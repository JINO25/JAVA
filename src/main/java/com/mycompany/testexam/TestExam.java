/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.testexam;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class TestExam extends Thread{
    public static void main(String[] args) {
        Thread t1 = new TestExam();
        t1.setName("thread 1");
        Thread t2 = new TestExam();
        t2.setName("thread 2");
        
        t1.setPriority(MAX_PRIORITY);
        t2.setPriority(MIN_PRIORITY);
        
        t1.start();
        t2.start();
                   
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(TestExam.class.getName()).log(Level.SEVERE, null, ex);
        }             
        
        System.out.println("Done");

    }

    @Override
    public void run() {            
      for(int i=1;i<=10;i++){
          System.out.println("Thread "+Thread.currentThread().getName()+": "+i);         
        }
      }  
}
