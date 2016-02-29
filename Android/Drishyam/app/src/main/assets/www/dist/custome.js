$('#accordion').accordion({active: false, collapsible: true});
$(function(){
  $('.datepicker').datepicker({
    dateFormat: "yy-mm-dd",
    timeFormat:  "HH:mm"
  });
})

$(function() {
   
    $( "#accordion" ).accordion();

    $("#accordion").click(function() {
    $( "#effect:visible" ).fadeOut('slow/600/fast', function() {
      // document['avg']['avengers'].value = "";
      // document['kk']['kangaroos'].value = "";
      // document['worklog']['issueLists'].value = "";
      // document['worklog']['timespent'].value = "";
      // document['worklog']['comment'].value = "";
    });
});

      
  }); 

Ladda.bind( '.button-demo button', { timeout: 2000 } );

      Ladda.bind( '.progress-demo button', {
        callback: function( instance ) {
          var progress = 0;
          var interval = setInterval( function() {
            progress = Math.min( progress + Math.random() * 0.1, 1 );
            instance.setProgress( progress );

            if( progress === 1 ) {
              instance.stop();
              clearInterval( interval );
            }
          }, 200 );
        }
      } );

function log(){

    var l = Ladda.create( document.querySelector( 'button' ) );
    var dates = document['worklog']['datepicker'].value;
    var emails = document['worklog']['email'].value;
    var shops = document['worklog']['shop'].value;
    var comments = document['worklog']['comment'].value;
    var names = document['worklog']['names'].value;

    l.start();
    l.stop();
    l.toggle();
    l.isLoading();
    l.setProgress(0-1);

    console.log(emails);
    console.log(shops);
    console.log(dates);
    console.log(comments);
    console.log(names);

 $.ajax({
   url: 'https://script.google.com/macros/s/AKfycbxBj6vL2oyoll3945s9AFY9bhjwBzzqHphz-md-S0o16lVJWO4/exec',
   type: 'post',
   dataType: 'json',
   data:{email:emails,date:dates,shop:shops,comment:comments,name:names},
  success: function(obj){
                 console.log(obj);
                 },
                 error: function() {
                 console.log("error");
                  }
          });
 

  }

var allItems = new Array();// just all the project names of avengers to be used for autocomplete
var individualName = new Array();//2d-contains project name, project key 
var individualDesc = new Array();//2d-contains project name, project key 
var individualImg = new Array();//2d-contains project name, project key 
var individualLoc = new Array();//2d-contains project name, project key 
var groupName = new Array();//2d-contains project name, project key 
var groupDesc = new Array();//2d-contains project name, project key 
var groupImg = new Array();//2d-contains project name, project key
var groupLoc = new Array();//2d-contains project name, project key
var sharedName = new Array();//2d-contains project name, project key 
var sharedDesc = new Array();//2d-contains project name, project key 
var sharedImg = new Array();//2d-contains project name, project key
var sharedLoc = new Array();//2d-contains project name, project key
var robotName = new Array();//2d-contains project name, project key 
var robotDesc = new Array();//2d-contains project name, project key 
var robotImg = new Array();//2d-contains project name, project key
var robotLoc = new Array();//2d-contains project name, project key
var recomName = new Array();//2d-contains project name, project key 
var recomDesc = new Array();//2d-contains project name, project key 
var recomImg = new Array();//2d-contains project name, project key
var recomLoc = new Array();//2d-contains project name, project key
var kangProject = new Array();// just all the project names of kangaroos to be used for autocomplete

individualName.push("Arduino UNO R3 and USB cable");
individualName.push("Acyclic Box for Arduino UNO R3");
individualName.push("Optical Sensor TCRT 5000 -x3");
individualName.push("Pushbutton");
individualName.push("Op-Amp LM339x1");
individualName.push("IC Socket for LM339");
individualName.push("Regulator L7805 x1");
individualName.push("Diode IN4001 x1");
individualName.push("Screw Terminator Block");
individualName.push("Capacitor Electrolytic");
individualName.push("Capacitor Electrolytic");
individualName.push("Breadboard");
individualName.push("Male Pinheader");
individualName.push("Female Pinheader");
individualName.push("L293 x1");
individualName.push("Variable resistor PCB 10 k");
individualName.push("LED x3");
individualName.push("ULN2003");
individualName.push("IC Socket for ULN2003");

individualDesc.push("(320 Baht)");
individualDesc.push("(100 Baht)");
individualDesc.push("(if you cannot find, use TCRT1000)");
individualDesc.push("(in thai, lazy to type)");
individualDesc.push("(3 Baht each)");
individualDesc.push("(14 pins) ขาเเบน x1");
individualDesc.push("(5 Baht)");
individualDesc.push("(.5 Baht)");
individualDesc.push("-Dinkle,20A,Pitch 5mm (in thai, lazy to type) x5 (5 Baht each)");
individualDesc.push("10 uF 25V x1");
individualDesc.push("1000 uF 35V x1 (3 Baht)");
individualDesc.push("Smallest size x1 (70 Baht)");
individualDesc.push("(PHSS40G10) 40 Pin x1 (3 Baht)");
individualDesc.push("in thai, lazy to type (in thai, lazy to type)  x10");
individualDesc.push("(49 Baht)");
individualDesc.push("(1 turn - Bourns in thai)x3 in thai (7 Baht each)");
individualDesc.push("(3mm of 5mm any colors - I prefer red @ 2 baht each)");
individualDesc.push("(6 Baht)");
individualDesc.push("(16 pins) ขาเเบน - x1");

individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');
individualImg.push('no image');


individualLoc.push('at www.arduino.in.th');
individualLoc.push('at www.arduino.in.th');
individualLoc.push('at ES');
individualLoc.push('at ES');
individualLoc.push('at ES');
individualLoc.push('at ES');
individualLoc.push('at ES');
individualLoc.push('at ES');
individualLoc.push('at ES');
individualLoc.push('at ES');
individualLoc.push('at ES');
individualLoc.push('at ES');
individualLoc.push('at ES');
individualLoc.push('at ES');
individualLoc.push('at ES');
individualLoc.push('at ES');
individualLoc.push('at ES');
individualLoc.push('at ES');
individualLoc.push('at ES');


groupName.push('Stranded wire 34 A WG');
groupName.push('Solid wire 22 A WG');
groupName.push('PCB Board');

groupDesc.push('100 ft x 1 (in thai)');
groupDesc.push('100 ft x 1 (Used for breadboard, Buy silver wire, not copper');
groupDesc.push('(in thai) Approx original iPhone size');

groupImg.push('no image');
groupImg.push('no image');
groupImg.push('no image');

groupLoc.push(' at Nattapong');
groupLoc.push(' at Nattapong');
groupLoc.push(' at Nattapong');

sharedName.push('Typical resistor 1 k 1/4 W Beige');
sharedName.push('Typical resistor 10 k 1/4 W Beige');
sharedName.push('Typical resistor 270 ohm 1/4 W Beige');
sharedName.push('Typical resistor 270 ohm 1/4 W Beige');
sharedName.push('Typical resistor 4.7 k 1/4 W Beige');

sharedDesc.push('(Blue is also fine, but a bit more expensive), x 100');
sharedDesc.push('(Blue is also fine, but a bit more expensive), x 100');
sharedDesc.push('(Blue is also fine, but a bit more expensive), x 100');
sharedDesc.push('(Blue is also fine, but a bit more expensive), x 100');
sharedDesc.push('(Blue is also fine, but a bit more expensive), x 100');

sharedImg.push('no image');
sharedImg.push('no image');
sharedImg.push('no image');
sharedImg.push('no image');
sharedImg.push('no image');

sharedLoc.push(' at Nattapong');
sharedLoc.push(' at Nattapong');
sharedLoc.push(' at Nattapong');
sharedLoc.push(' at Nattapong');
sharedLoc.push(' at Nattapong');

robotName.push('Gear DC motor');
robotName.push('Aluminium Motor Holder');
robotName.push('Rollerblade');// 3.5" 6 mm x 2
robotName.push('Aluminium Profile');
robotName.push('Aluminium Profile');
robotName.push('D Bracket');
robotName.push('Ball wheel');
robotName.push('Bluetooth Serial Module');
robotName.push('Screw Shield for Arduino');
robotName.push('Motor Drive Module');
robotName.push('Li-Po 3 Cell 1500 mAh + Male Connector ');

robotDesc.push(' 12 V, 100 rpm, Silver-gold, Shaft 6 mm X 2');
robotDesc.push(' x2 (75 Baht each)');
robotDesc.push(' 3.5" 6 mm x 2 (120 Baht each)');
robotDesc.push(' 20x20 mm, 120 mm long x2 (17 Baht each)');
robotDesc.push(' 20x20 mm, 70 mm long x4 (10 Baht each)');
robotDesc.push(' for 20x20 mm x 6 (40 Baht each)');
robotDesc.push(' 1 inch x1 (85 Baht)');
robotDesc.push(' (HC-05 Master/Slave mode) x1 (220 Baht)');
robotDesc.push(' x1 (150 Baht)');
robotDesc.push(' (L298N) x2 (145 Baht each)');
robotDesc.push(' (400 + 20 Baht)');

robotImg.push('no image');
robotImg.push('no image');
robotImg.push('no image');
robotImg.push('no image');
robotImg.push('no image');
robotImg.push('no image');
robotImg.push('no image');
robotImg.push('no image');
robotImg.push('no image');
robotImg.push('no image');
robotImg.push('no image');

robotLoc.push('Sangtawan');
robotLoc.push('Sangtawan');
robotLoc.push('Sangtawan');
robotLoc.push('Sangtawan');
robotLoc.push('Sangtawan');
robotLoc.push('Sangtawan');
robotLoc.push('Sangtawan');
robotLoc.push('Arduino.in.th');
robotLoc.push('Arduino.in.th');
robotLoc.push('Arduino.in.th');
robotLoc.push('Sa Pan Lek');

recomName.push('Solderless Breadboard');
recomName.push('Wire striper');
recomName.push('Cutting piler');
recomName.push('Long nose piler');
recomName.push('Flat and philip screwdrivers');
recomName.push('Multimeter ');
recomName.push('Soldering iron + solder (in thai, lazy to type)');

recomDesc.push('no description');
recomDesc.push('no description');
recomDesc.push('no description');
recomDesc.push('no description');
recomDesc.push('no description');
recomDesc.push('(The low-cost 300-400 Baht ones are usable, but not great)');
recomDesc.push('if you never use one, try at lab first(Price range: 100-3000)');

recomImg.push('no image');
recomImg.push('no image');
recomImg.push('no image');
recomImg.push('no image');
recomImg.push('no image');
recomImg.push('no image');
recomImg.push('no image');

recomLoc.push('Ban Mau');
recomLoc.push('Ban Mau');
recomLoc.push('Ban Mau');
recomLoc.push('Ban Mau');
recomLoc.push('Ban Mau');
recomLoc.push('Ban Mau');
recomLoc.push('Ban Mau');
//(The low-cost 300-400 Baht ones are usable, but not great)
var children = individualName.concat(groupName).concat(sharedName).concat(robotName).concat(recomName); 
var children2 = individualDesc.concat(groupDesc).concat(sharedDesc).concat(robotDesc).concat(recomDesc); 
var children3 = individualImg.concat(groupImg).concat(sharedImg).concat(robotName).concat(recomImg); 
var children4 = individualLoc.concat(groupLoc).concat(sharedLoc).concat(robotLoc).concat(recomLoc); 
var checkbox = new Array();
for (var i = 0; i< children.length ;i++) {
  checkbox[i] = 'unchecked';
};

$(function() {
    var availableTags = children;
    $( "#avengers" ).autocomplete({
      maxShowItems: 5,
      source: availableTags
    });
  });

$(function() {
    var availableTags = individualName;
    $( "#individual" ).autocomplete({
      maxShowItems: 5,
      source: availableTags
    });
  });

$(function() {
    var availableTags = groupName;
    $( "#group" ).autocomplete({
      maxShowItems: 5,
      source: availableTags
    });
  });

$(function() {
    var availableTags = sharedName;
    $( "#shared" ).autocomplete({
      maxShowItems: 5,
      source: availableTags
    });
  });

$(function() {
    var availableTags = robotName;
    $( "#robot" ).autocomplete({
      maxShowItems: 5,
      source: availableTags
    });
  });

$(function() {
    var availableTags = recomName;
    $( "#recommended" ).autocomplete({
      maxShowItems: 5,
      source: availableTags
    });
  });

// $('table').filterTable({
//     callback: function(term, table) {
//         table.find('tr').removeClass('striped').filter(':visible:even').addClass('striped');
//     }
// });
function showTable(){
   $( "#alltab" ).empty();
    

   showHide
      var html = ''+'<table id="jms">  <thead> <tr>'+
                '<th scope="col" title="ProductCheck">Checklist</th>'+
                '<th scope="col" title="ProductNo"> ..#..</th>'+
                '<th scope="col">Product_Name</th>'+
                 '<th scope="col">Product_Description</th>'+
                '<th scope="col">Product_Image</th>'+
                '<th scope="col">Product_Location</th>'+
                '<th scope="col">Contact</th>'+
                '</tr>  </thead> <tbody>';
          for (var i = 0; i < children.length; i++) {
              var j = i+1;
              html = html+'<tr><td><input class="input_check" type="checkbox" name="jrk" id="'+i+'" value="jsk"'+checkbox[i]+' onchange="toggleCheckbox(this)"></td>';
              html=html+'<td> '+j+'</td><td>  '+children[i]+'</td><td>  '+children2[i];
              html = html+'</td><td>  '+children3[i]+'</td><td>  '+children4[i]+'</td>';
              html = html+'</td><td> 09-2282-8604</td></tr>';
            }
            html=html+'</tbody> </table>';
          $('#alltab').append(html);
          $(document).ready(function() {
        // apply filterTable to all tables on this page
        $('table').filterTable({filterExpression: 'filterTableFindAll'});
    });

           if (this.value=="show"){

            this.value = "hide";
            this.text = "Hide the whole lists";

    } else{
      
     this.value = "show";
     this.text = "Load the whole lists";
     $( "#alltab" ).empty();
   }

}

function toggleCheckbox(element)
 {
   checkbox[element.id] = "checked";
   console.log(element.id+" is selected");
 }

$("#avengers").on("autocompleteselect", function( event, ui ) {

$( "#altab" ).empty();
    var select = document.getElementById("avengers").value;
    var j=0;
    var key = "";
    var options = '';

  for(i=0; i < children.length; i++) {
      if( typeof children[i] !== 'undefined'|| 'undefined' != children[i]){

            if (children[i]==select) {              
                                                  j=i;
                                                  break;          
                                               };
          }
  }

  document.getElementById("alltab").innerHTML = select+"'s Info";

    var table = document.getElementById("altab");
    var header = table.createTHead();
    var row = header.insertRow(0);
    var cell = row.insertCell(0);
    cell.innerHTML = "<b>Product Name</b><br />"+children4[j];

    var table = document.getElementById("altab");
    var header = table.createTHead();
    var row = header.insertRow(0);
    var cell = row.insertCell(0);
    cell.innerHTML = "<b>Product Description</b><br />"+children3[j];


    var table = document.getElementById("altab");
    var header = table.createTHead();
    var row = header.insertRow(0);
    var cell = row.insertCell(0);
    cell.innerHTML = "<b>Product Image</b><br />"+children2[j];


    var table = document.getElementById("altab");
    var header = table.createTHead();
    var row = header.insertRow(0);
    var cell = row.insertCell(0);
    cell.innerHTML = "<b>Product Location</b><br />"+children[j];
} );

$("#avengers").on("autocompleteselect", function( event, ui ) {
  
$( "#altab" ).empty();
    var select = document.getElementById("avengers").value;
    var j=0;
    var key = "";
    var options = '';

  for(i=0; i < children.length; i++) {
      if( typeof children[i] !== 'undefined'|| 'undefined' != children[i]){

            if (children[i]==select) {              
                                                  j=i;
                                                  break;          
                                               };
          }
  }

  document.getElementById("alltab").innerHTML = select+"'s Info";

    var table = document.getElementById("altab");
    var header = table.createTHead();
    var row = header.insertRow(0);
    var cell = row.insertCell(0);
    cell.innerHTML = "<b>Product Name</b><br />"+children4[j];

    var table = document.getElementById("altab");
    var header = table.createTHead();
    var row = header.insertRow(0);
    var cell = row.insertCell(0);
    cell.innerHTML = "<b>Product Description</b><br />"+children3[j];


    var table = document.getElementById("altab");
    var header = table.createTHead();
    var row = header.insertRow(0);
    var cell = row.insertCell(0);
    cell.innerHTML = "<b>Product Image</b><br />"+children2[j];


    var table = document.getElementById("altab");
    var header = table.createTHead();
    var row = header.insertRow(0);
    var cell = row.insertCell(0);
    cell.innerHTML = "<b>Product Location</b><br />"+children[j];
} );
    
function runEffect() {
var options = { to: { width: 280, height: 185 } };

 $( "#effect" ).show( "size", options, 500, callback );
}

 function callback() {
      setTimeout(function() {
        $( "#effect:visible" ).removeAttr( "style" );
      }, 1000 );
    };
 
    // set effect from select menu value
    $( "#button" ).click(function() {
      runEffect();
    });

    $( "#buttonva" ).click(function() {
      runEffect();
    });
 
    $( "#effect" ).hide();


 $(document).ready(function () {
                var text2 = $("#individual").tautocomplete({
                    width: "500px",
                    columns: ['Prouct', 'Description', 'Image', 'Loacation'],
                    data: function () {
                        try{
                          var data = new Array();
                          for (var i = 0; i < individualName.length; i++) {
                           data[i] = [{"product":individualName[i],"description":individualDesc[i],"image":individualImg[i],"location":individualLoc[i]}];
                            console.log(data[i][0].product);
                          };
                            }
                        catch(e)
                        {
                            alert(e)
                        }
                        var filterData = [];

                        var searchData = eval("/" + text2.searchdata() + "/gi");
                      
                         $.each(data[0], function(i,v)
                        {
                            if (v.product.search(new RegExp(searchData)) != -1) {
                                filterData.push(v);
                            }
                        });
                      
                        
                        return filterData;
                    },
                    onchange: function () {
                       console.log("Jai radhekrishna, its working");
                    }
                });
            });