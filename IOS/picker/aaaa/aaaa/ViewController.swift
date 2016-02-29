//
//  ViewController.swift
//  aaaa
//
//  Created by 6272 on 9/28/2558 BE.
//  Copyright (c) 2558 6272. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {

    @IBOutlet var select: UIView!
    let array1=["1","2","3","4"]
    let array2=["12","22","32","42"]
    
    
    @IBOutlet weak var picker1: UIPickerView!
    
    func pickerView(pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String! {
        
        if component == 0{
            return array1[row]
        }
        else{
            return array2[row]
        }
    }
    
    func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int {
        return 2
    }
    
    func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if component == 0{
            return array1.count
        }else{
            
            
            return array2.count
        }
        
    }
    @IBAction func selectMethod() {
        let vFood = array1[picker1.selectedRowInComponent(0)]
        
        let vDrink = array2[picker1.selectedRowInComponent(1)]
        let m1 =  "Your selected " + vFood + "for main"
        let m2 = "Jai radhekrishna " + vDrink + "Jai \r\n Gurudev bhagwan"
        
        let message = m1+m2
        
        let a = UIAlertController(title: "JRK", message: message, preferredStyle: .Alert)
        
        let okButton = UIAlertAction(title: "ok", style: .Default, handler: nil)
        
        a.addAction(okButton)
        self.presentViewController(a,animated:true,completion:nil)
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        picker1.selectRow(1, inComponent: 0,animated: true)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

