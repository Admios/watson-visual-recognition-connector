Recognize Text File System Image Demo
===========================

INTRODUCTION
------------
The Recognize Text File System Image Demo project offer one use case using this flow:

* **ExtractTextFromInvoicesFlow** - Automatize the ingestion of digitalized invoices saved in a file system, for then be processed by Mule and Watson to extract the text and save the invoice with his metadata in a management system.


HOW TO RUN DEMO
---------------

### Prerequisites
In order to build run this demo project you'll need;

* Anypoint Studio with Mule ESB 3.6 Runtime or higher.
* Watson Visual Recognition v1.0.0 or higher.
* Watson Visual Recognition api key from Bluemix.

### Import the demo

With Anypoint Studio up and running, open the Import wizard from the File menu. A pop-up wizard will offer you the chance to pick Anypoint Studio Project from External Location. On the next wizard window point Project Root to the location of the demo project and select the Server Runtime as Mule Server 3.8.0 CE or EE. Once successfully imported the studio will automatically present the Mule Flows.

From the Package Explorer view, expand the demo project and open the mule-app.properties file. Fill in the api key of Watson.

### Test the flows

- **ExtractTextFromInvoicesFlow**

Run the demo project, the application will start cheking the folder **invoices/input** for new images, then it'll execute the flow **SaveInManagementSystem** to save the results if you choose to implement this flow.:

**Input**: Put the demo image **invoices/invoices.png** in the folder **invoices/input**.

**Response**: The demo image should be in the path **invoices/output/invoices.png**, and you can see the results in the console.

```javascript
INFO  2016-09-12 15:58:52,429 [[vrdemo].ExtractTextFromInvoicesFlow.stage1.02] org.mule.api.processor.LoggerMessageProcessor: {
  "images_processed": 1,
  "images": [
    {
      "text": "invoice",
      "words": [
        {
          "word": "invoice",
          "location": {
            "width": 127,
            "height": 46,
            "left": 77,
            "top": 87
          },
          "score": 0.951472,
          "line_number": 0
        }
      ],
      "image": "Watson-VR-6f3898e2-a2c3-41e4-8690-d23c3c14b9513491730484826278262.png"
    }
  ]
}
INFO  2016-09-12 15:58:52,441 [[vrdemo].ExtractTextFromInvoicesFlow.stage1.02] org.mule.api.processor.LoggerMessageProcessor: {file=[B@2611d832, text=invoice}
```

SUMMARY
-------

Congratulations! You have imported the Recognize Text File System Image Demo project and used the Watson Visual Recognition Connector to extract valuable data from an image. 