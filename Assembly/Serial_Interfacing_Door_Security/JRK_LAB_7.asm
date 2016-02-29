DSP1_BUFFER	EQU	030H	; For keep DSP1 Data
DSP2_BUFFER	EQU	031H	; For keep DSP2 Data
KPAD_DATA	EQU	032H	; For keep Keypad Data
Password_field1	EQU	033H	; For password pin1
Password_field2	EQU	034H	; For password pin2
Password_field3	EQU	035H	; For password pin3
Password_field4	EQU	036H	; For password pin4
FLAG    EQU 02FH  ; various purpose flag
DATA1	EQU	037H  ; keypress store1
DATA2	EQU	038H  ; keypress store2
DATA3	EQU	039H  ; keypress store3
DATA4	EQU	03AH  ; keypress store4
INPUT_CHAR1 EQU 023H ;input character
INPUT_CHAR3 EQU 024H ;input character
INPUT_CHAR2 EQU 025H ;input character
;----------------------------------------------------------------------------
;Define Vectors for ISR
;----------------------------------------------------------------------------
	ORG   0
	LJMP  MAIN 		;3-byte instruction
   	LJMP  EX0ISR 	;03H EX0 vector
   	ORG   000BH 		;Timer0 vector
	LJMP  T0ISR
   	ORG   001BH 		;Timer1 vector
	LJMP  T1ISR
;	ORG   0023H 		;Serial Port vector
	;LJMP  SPISR
;----------------------------------------------------------------------------
; Main Program.
;----------------------------------------------------------------------------
  		 ORG   003BH
MAIN:	MOV	    P0,#0	; Clear Databus
		SETB	P1.2		; Clear DSP1
		SETB	P1.3		; Clear DSP2
		MOV	P2,#11111111B	; Clear status keypad and 1-Wire
		MOV R1,#DATA1
	  	SETB   IT0      ;logic low 
		MOV  IE, #81H 	; enable EXT 0 only
	   	MOV FLAG,#0
;----------------------------------------------------------------------------
; Initial SCON and Bauad rate condition
;----------------------------------------------------------------------------
		MOV	SCON,#050H	; Mode1 RX Disable
       	MOV	TMOD,#20H	; Set T1 to mode2
		MOV	TH1,#0FAH	;(-6)4800 bps Timer1
		MOV	TL1,#0FAH	;
		SETB TR1
        CLR	TI		; Clear TI
        CLR	RI		; Clear RI
;----------------------------------------------------------------------------
; INITIALIZE MEM_LOC And DSP
;----------------------------------------------------------------------------
MOV Data1,#0
MOV Data2,#0
MOV Data3,#0
MOV Data4,#0
SETB FLAG.4     ; password field blank?
		
MOV	DSP1_BUFFER,#1	; Clear DSP1 Buffer
MOV	DSP2_BUFFER,#1	; Clear DSP2 Buffer
;----------------------------------------------------------------------------
; Serial Port Interupt Service Routine
;----------------------------------------------------------------------------
;SPISR: JB TI,jsk	
RISR:  MOV INPUT_CHAR3,INPUT_CHAR2 	 	
       MOV INPUT_CHAR2,INPUT_CHAR1 
	   CLR RI
	   MOV A, SBUF 
       MOV INPUT_CHAR1,A

MOV	DSP2_BUFFER,#12 
MOV	DSP1_BUFFER,#12
ACALL SHOW_DSP
ACALL Delay_100ms

MOV A,#0
MOV DPTR,#CHAR1
MOVC A,@A+DPTR
CJNE A,INPUT_CHAR3,LOOP

		MOV A,#0
		MOV DPTR,#CHAR2
		MOVC A,@A+DPTR
		CJNE A,INPUT_CHAR2,LOOP
MOV A,#0
MOV DPTR,#CHAR3
MOVC A,@A+DPTR
CJNE A,INPUT_CHAR1,LOOP
		
			   SETB FLAG.2
    		   SJMP LOOP
;----------------------------------------------------------------------------
; Pin Enter 
;----------------------------------------------------------------------------
LOOP:  	JB RI,RISR

lol:	ACALL	GET_KPAD	; Get Keypad Data
		MOV	A,KPAD_DATA
		CJNE A,#0BH,Here
	    AJMP Warning
      
Here:  	CJNE A,#0DH,CheckBlank
	    AJMP SAVED

CheckBlank: JB FLAG.0,NEXTT ;to check if nothing enterd
			
Task1: 	JB	FLAG.1 ,SHOW 	; Check Keypad still pressed? KEYPRESSED
		SETB	FLAG.1		; Set bit keypressed  KEYPRESSED
		CLR FLAG.4

DigitEntered:AJMP DIGITstore ;confirmed digit

LEFT_SHIFT:	MOV	DSP2_BUFFER,DSP1_BUFFER ; Shift Display to Right
	     	MOV	DSP1_BUFFER,KPAD_DATA	; Shift Keypad data in
	     	AJMP	SHOW		; Jump to Show Display

NEXTT:	CLR	FLAG.1		; Clear bit keypressed  KEYPRESSED
SHOW:	ACALL	SHOW_DSP	; Call Show DSP Subroutine
		AJMP	LOOP		; Jump to loop

;----------------------------------------------------------------------------
;Digit Storing
;----------------------------------------------------------------------------
DIGITstore:	MOV DATA4,DATA3
			MOV DATA3,DATA2
			MOV DATA2,DATA1
			MOV DATA1,KPAD_DATA
			SJMP LEFT_SHIFT
;----------------------------------------------------------------------------
; Keypad Scan key Subroutine
;----------------------------------------------------------------------------
GET_KPAD:CLR FLAG.0
		MOV	P2,#07FH	; Pull P2 to High
		MOV	KPAD_DATA,#1	; Clear Keypad Data
	
CHK_COL0:	CLR	P2.6 		; Begin Scan Column 0 KPAD_COL0
		MOV	A,P2		; Get Port2 Value
		ANL	A,#00FH		; Get only lower 4 bit
		CJNE	A,#00FH,COL0_DETECT ; Check All rows '1'?
		AJMP	CHK_COL1	; All rows '1' => check next column

COL0_DETECT:	MOV	KPAD_DATA,#02	; Initial KPAD_DATA = 1
		AJMP	GET_ROW		; Jump to get row value

CHK_COL1:	SETB	P2.6		; Stop Scan Column 0  KPAD_COL0
		CLR	P2.5		; Begin Scan Column 1 KPAD_COL1
		MOV	A,P2		; Get Port2 Value
		ANL	A,#00FH		; Get only lower 4 bit
		CJNE	A,#00FH,COL1_DETECT ; Check All rows '1'?
		AJMP	CHK_COL2	; All rows '1' => check next column

COL1_DETECT:	MOV	KPAD_DATA,#03	; Initial KPAD_DATA = 2
		AJMP	GET_ROW	; Jump to get row value

CHK_COL2:	SETB	P2.5		; Stop Scan Column 1 KPAD_COL1
		CLR	P2.4		; Begin Scan Column 2 KPAD_COL2
		MOV	A,P2		; Get Port2 Value
		ANL	A,#00FH		; Get only lower 4 bit
		CJNE	A,#00FH,COL2_DETECT ; Check All rows '1'?
		SETB FLAG.0
		RET			; All rows '1' => return

COL2_DETECT:	MOV	KPAD_DATA,#04	; Initial KPAD_DATA = 3

GET_ROW:CLR	P2.6		; Enable all Column to find Crosspoint KPAD_COL0
		CLR	P2.5		; KPAD_COL1
		CLR	P2.4 		;KPAD_COL2
		JB	P2.0 ,CHK_ROW1 ; Check Row 0 Detect?
		RET			; Row 0 Detect => return

CHK_ROW1:	JB	P2.1 ,CHK_ROW2 	; Check Row 2 Detect? KPAD_ROW1
		MOV	A,KPAD_DATA	; Add 3 with KPAD_DATA
		ADD	A,#3
		MOV	KPAD_DATA,A
		RET			; Return

CHK_ROW2:	JB	P2.2, CHK_ROW3 	; Check Row 2 Detect? KPAD_ROW2
		MOV	A,KPAD_DATA	; Add 6 with KPAD_DATA
		ADD	A,#6
		MOV	KPAD_DATA,A
		RET			; Return

CHK_ROW3:	MOV	A,KPAD_DATA	; Add 9 with KPAD_DATA
		ADD	A,#9
		MOV	KPAD_DATA,A
		RET			; Return
;----------------------------------------------------------------------------
; Show DSP Subroutine
;----------------------------------------------------------------------------
SHOW_DSP:	MOV	R5,#5	; Set loop 5 times
SCAN_DSP_LOOP:	MOV	A,DSP1_BUFFER	; Restore DSP1 to Display
      	MOV	DPTR,#DSP_BLANK ; Move DIGIT Start Pointer
		MOVC	A,@A+DPTR	; Get ROM Data from Pointer+ACC.
		MOV	P0,A		; Out ACC. to DATABUS
		CLR	P1.2		; Enable DSP1
		ACALL	DELAY_10ms	; Delay
		SETB	P1.2		; Disable DSP1

		MOV	A,DSP2_BUFFER	; Restore DSP2 to Display
	    MOV	DPTR,#DSP_BLANK ; Move DIGIT Start Pointer
		MOVC	A,@A+DPTR	; Get ROM Data from Pointer+ACC.
		MOV	P0,A		; Out ACC. to DATABUS
		CLR	P1.3		; Enable DSP2
		ACALL	DELAY_10ms	; Delay
		SETB	P1.3		; Disable DSP1
		DJNZ	R5,SCAN_DSP_LOOP; Do until 5 times
		RET
;----------------------------------------------------------------------------
;Clear data field
;----------------------------------------------------------------------------
CLear_Data_field:
		MOV Data1,#0
		MOV Data2,#0  
		MOV Data3,#0    ;clear password field
		MOV Data4,#0
		SETB FLAG.4
		CLR FLAG.5
		CLR FLAG.6
		CLR FLAG.7
			RET
;----------------------------------------------------------------------------
;Show text on PC
;----------------------------------------------------------------------------
TX_TEXT:CLR TR1
		CLR TR0
		MOV	SCON,#050H	; Mode1 RX Disable
       	MOV	TMOD,#20H	; Set T1 to mode2
		MOV	TH1,#0FAH	;(-6)4800 bps Timer1
		MOV	TL1,#0FAH	;
		SETB TR1
        CLR	TI		; Clear TI
        CLR	RI		; Clear RI
;-----------------------------------------
TX_LOOP: CLR	A		; Clear ACC.
		 MOVC	A,@A+DPTR	; Get Data from ROM with Pointer
		 INC	DPTR		; Increase Pointer
		 CJNE	A,#0,TX_CHAR	; Check null terminated 000H Char.
		 RET			; End => Return

TX_CHAR:MOV	SBUF,A	; Send Data to SBUF
	    JNB	TI,$		; Wait until TX already (TI=1)
jsk:    CLR	TI		; Clear TI
   		ACALL	DELAY_100ms	; Delay	each character
   		AJMP	TX_LOOP		; Jump to TX_LOOP
;----------------------------------------------------------------------------
; Warnings (*entered, blank password...)
;----------------------------------------------------------------------------
Warning:ACALL bell2
		ACALL bell2
		ACALL bell2

	    ACALL CLear_Data_field

		MOV	DPTR,#SERIAL_TEXT ; Set Pointer Serial TX
     	ACALL	TX_TEXT		; TX Text to Serial Port
			 AJMP LOOP
;------------------------------------
Warning2:ACALL bell2
		ACALL bell2
		ACALL bell2

DPR0:	JNB FLAG.4,DPR1
		MOV	DPTR,#SERIAL_TEXT5 ; Set Pointer Serial TX
		SJMP gg

DPR1:	JNB FLAG.5,DPR2
		MOV	DPTR,#SERIAL_TEXT6 ; Set Pointer Serial TX
		SJMP gg

DPR2:	JNB FLAG.6,DPR3
		MOV	DPTR,#SERIAL_TEXT7 ; Set Pointer Serial TX
		SJMP gg

DPR3:	MOV	DPTR,#SERIAL_TEXT8 ; Set Pointer Serial TX
		
gg: 	ACALL CLear_Data_field
	   	ACALL	TX_TEXT		; TX Text to Serial Port
		AJMP LOOP
;-------------------------------------
Warning3:ACALL bell2
		ACALL bell2
		ACALL bell2

	    ACALL CLear_Data_field

		MOV	DPTR,#SERIAL_TEXT10 ; Set Pointer Serial TX
     	ACALL	TX_TEXT		; TX Text to Serial Port
	JumP:	 AJMP LOOP
;----------------------------------------------------------------------------
; Saved password
;----------------------------------------------------------------------------
SAVED: 
	JNB FLAG.2, Warning3
CheckPoint1:;check if all blank
		JNB FLAG.4,CheckPoint2
		AJMP Warning2
CheckPoint2:;check if only 1 pin entered
		MOV A,DATA2
		SETB FLAG.5
		JZ Warning2
CheckPoint3:;check if only 2 pins entered
		MOV A,DATA3
		CLR FLAG.5
		SETB FLAG.6
		JZ Warning2
CheckPoint4:;check if only 3 pins entered
		MOV A,DATA4
		CLR FLAG.6
		SETB FLAG.7
		JZ Warning2

Store:	MOV Password_field1,Data4		
		MOV Password_field2,Data3
		MOV Password_field3,Data2
		MOV Password_field4,Data1
			
    	ACALL bell1
		ACALL bell1
		ACALL bell1

	    ACALL CLear_Data_field
		ACALL Temp
       	MOV	DPTR,#SERIAL_TEXT2 ; Set Pointer Serial TX
     	ACALL	TX_TEXT		; TX Text to Serial Port
	JumP1:	 AJMP LOOP
;----------------------------------------------------------------------------
; Password comparison 1
;----------------------------------------------------------------------------
PC:	    MOV A,#0
	   MOV DPTR,#Password_ID1
	   MOVC A,@A+DPTR
        CJNE A,Data4,MisMatch	
		SJMP Checkk1

Checkk1: MOV A,#0
	   MOV DPTR,#Password_ID2
	   MOVC A,@A+DPTR
        CJNE A,Data3,MisMatch	
		SJMP Checkk2

Checkk2: MOV A,#0
	   MOV DPTR,#Password_ID3
	   MOVC A,@A+DPTR
        CJNE A,Data2,MisMatch	
		SJMP Checkk3

Checkk3: MOV A,#0
	   MOV DPTR,#Password_ID4
	   MOVC A,@A+DPTR
        CJNE A,Data1,MisMatch	
		SJMP Matched
;----------------------------------------------------------------------------
; Interrupt related program
;----------------------------------------------------------------------------
EX0ISR:	JNB FLAG.2,PC
		MOV A,Data4
		CJNE A,Password_field1,MisMatch	
		SJMP Check1

Check1:	MOV A,Data3
		CJNE A,Password_field2,MisMatch	
		SJMP Check2

Check2:	MOV A,Data2
		CJNE A,Password_field3,MisMatch	
		SJMP Check3

Check3:	MOV A,Data1
		CJNE A,Password_field4,MisMatch	
		SJMP Matched
MisMatch:
 	    JNB FLAG.4,jrk
		MOV DPTR,#SERIAL_TEXT9 ; Set Pointer Serial TX
		SJMP caller
jrk:    MOV DPTR,#SERIAL_TEXT3 ; Set Pointer Serial TX
caller:	ACALL	TX_TEXT		; TX Text to Serial Port
	    ACALL CLear_Data_field
		CLR TR1
		MOV  TMOD, #11H 	;16-bit timer mode
		MOV   R4, #40 	;40x50000 us = 2 second
 		SETB   TF0 		;force Timer0 interrupt
 		SETB   TF1 		;force Timer1 interrupt
 		SETB   ET0 		;enable Timer0 interrupt
 		SETB   ET1 		;enable Timer1 interrupt 
 		RETI 		

T0ISR: 	CLR     TR0 			;stop Timer0
		DJNZ   R4, SKIP		;if not 20th time, exit
 		CLR     ET0 			;if 20th disable tone
 		CLR     ET1 			;disable itself
		MOV DPTR,#SERIAL_TEXT12 ; Set Pointer Serial TX
		ACALL	TX_TEXT		; TX Text to Serial Port	
 		LJMP  EXIT

SKIP: 	MOV  TH0,#HIGH(-50000) 	; 0.05 sec delay
 		MOV  TL0,#LOW(-50000)
 		SETB  TR0

EXIT: 	RETI

T1ISR: 	CLR      TR1
 		MOV   TH1,#HIGH(-1000) 	;count for 400 Hz
 		MOV   TL1,#LOW(-1000)
 		CPL      P1.7 			; music
 		SETB   TR1
 		RETI
;----------------------------------------------------------------------------
; If password Matched
;----------------------------------------------------------------------------
Matched:ACALL bell
		ACALL bell	
		ACALL bell

	    ACALL CLear_Data_field

		MOV	DPTR,#SERIAL_TEXT4 ; Set Pointer Serial TX
     	ACALL	TX_TEXT		; TX Text to Serial Port

		AJMP LOOP		
;----------------------------------------------------------------------------
; Bells, the blinks
;----------------------------------------------------------------------------
bell: 	MOV	DSP2_BUFFER,#0 ;Digit1 accepted
		MOV	DSP1_BUFFER,#0 
		ACALL	SHOW_DSP
		MOV	DSP2_BUFFER,#14 ;Digit1 accepted
		MOV	DSP1_BUFFER,#14 
		ACALL	SHOW_DSP	
		RET 

bell1:	MOV	DSP2_BUFFER,#0 ;Digit1 accepted
		MOV	DSP1_BUFFER,#0  
		ACALL	SHOW_DSP	
		MOV	DSP2_BUFFER,#11 ;Digit1 accepted
		MOV	DSP1_BUFFER,#11 
		ACALL	SHOW_DSP	
		RET 

bell2: 	MOV	DSP2_BUFFER,#0 ;Digit1 accepted
		MOV	DSP1_BUFFER,#0 
		ACALL	SHOW_DSP
		MOV	DSP2_BUFFER,#13 ;Digit1 accepted
		MOV	DSP1_BUFFER,#13 
		ACALL	SHOW_DSP	
		RET 

;----------------------------------------------------------------------------
; temp 
;----------------------------------------------------------------------------
Temp: MOV DSP1_BUFFER,Password_field1
			MOV	DSP2_BUFFER,Password_field1
			ACALL SHOW_DSP	
			ACALL DELAY_100ms	
		MOV	DSP1_BUFFER,Password_field2
			MOV	DSP2_BUFFER,Password_field2
			ACALL SHOW_DSP	
			ACALL DELAY_100ms	
		MOV	DSP1_BUFFER,Password_field3
			MOV	DSP2_BUFFER,Password_field3
			ACALL SHOW_DSP	
			ACALL DELAY_100ms	
		MOV	DSP1_BUFFER,Password_field4
			MOV	DSP2_BUFFER,Password_field4
			ACALL SHOW_DSP	
			ACALL DELAY_100ms	
			RET
;----------------------------------------------------------------------------
; Dummy Delay time 1m,10ms,1s
;----------------------------------------------------------------------------

DELAY_10ms:	MOV	R7,#010		; Do 10 times
DELAY_10ms_1:	MOV	R6,#0E6H	; Each loop = 1 ms
DELAY_10ms_2:	NOP
		NOP
		DJNZ	R6,DELAY_10ms_2
		DJNZ	R7,DELAY_10ms_1
		RET
DELAY_100ms:	MOV	R7,#100		; Do 100 times
DELAY_100ms_1:	MOV	R6,#0E6H	; Each loop = 1 ms
DELAY_100ms_2:	NOP
		NOP
		DJNZ	R6,DELAY_100ms_2
		DJNZ	R7,DELAY_100ms_1
		RET
;----------------------------------------------------------------------------
;Define Constant < Store in Flash EEPROM Program Memory >
;----------------------------------------------------------------------------
SERIAL_TEXT: DB "  Only digits Please Enter_Again"
			 DB 0DH,0AH ,00H
SERIAL_TEXT2: DB "  Password Saved :)"
			 DB 0DH,0AH ,00H
SERIAL_TEXT3: DB "  Password MisMatch~OPEN :("
			 DB 0DH,0AH ,00H
SERIAL_TEXT4: DB "  Password Matched! :-) Reset the board"
			 DB 0DH,0AH ,00H
SERIAL_TEXT5: DB "  Password Field is blank, Enter 4 digits"
			 DB 0DH,0AH ,00H
SERIAL_TEXT6: DB "  You've entered only 1 pin enter 4 pins!!"
			 DB 0DH,0AH ,00H
SERIAL_TEXT7: DB "  You've entered only 2 pins enter 4 pins!!"
			 DB 0DH,0AH ,00H
SERIAL_TEXT8: DB "  You've entered only 3 pins enter 4 pins!!"
			 DB 0DH,0AH ,00H
SERIAL_TEXT9: DB "  Enter 4 pins before pressing S!!"
			 DB 0DH,0AH ,00H
SERIAL_TEXT10: DB "  Write me CAT before saving your own password "
			 DB 0DH,0AH ,00H
SERIAL_TEXT12: DB "  Try harder"
			 DB 0DH,0AH ,00H

;		Segment	.GFEDCBA
DSP_BLANK:	DB	00000000B
DSP_DOT:	DB	10000000B
DSP_NUM1:	DB	00000110B
DSP_NUM2:	DB	01011011B
DSP_NUM3:	DB	01001111B
DSP_NUM4:	DB	01100110B
DSP_NUM5:	DB	01101101B
DSP_NUM6:	DB	01111101B
DSP_NUM7:	DB	00000111B
DSP_NUM8:	DB	01111111B
DSP_NUM9:	DB	01101111B
DSP_STAR:	DB	01110110B
DSP_NUM0:	DB	00111111B
DSP_HASH:	DB	01100011B
DSP_AA:	 	DB	01110111B

PASSWORD_ID1:	DB	00000010B
PASSWORD_ID2:	DB	00000011B
PASSWORD_ID3:	DB	00000010B
PASSWORD_ID4:	DB	00000110B

CHAR1: DB "C"
CHAR2: DB "A"
CHAR3: DB "T"
	END









