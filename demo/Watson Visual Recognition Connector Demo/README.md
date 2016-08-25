Watson Visual Recognition Connector Demo
===========================

INTRODUCTION
------------
The Watson Visual Recognition connector demo project consists of the three flows:

* ClassifyImageFlow - Provides an example on how to classify an image from an URL.
* DetectFacesFlow - Provides an example on how to detect faces in an image from an URL.
* RecognizeTextFlow - Provides an example on how to extract the text in an image from an URL.

HOW TO RUN DEMO
---------------

### Prerequisites
In order to build run this demo project you'll need;

* Anypoint Studio with Mule ESB 3.6 Runtime or higher.
* Watson Visual Recognition v1.0.0 or higher.
* Watson Visual Recognition api key from Bluemix.

### Test the flows

With Anypoint Studio up and running, open the Import wizard from the File menu. A pop-up wizard will offer you the chance to pick Anypoint Studio Project from External Location. On the next wizard window point Project Root to the location of the demo project and select the Server Runtime as Mule Server 3.8.0 CE or EE. Once successfully imported the studio will automatically present the Mule Flows.

From the Package Explorer view, expand the demo project and open the mule-app.properties file. Fill in the api key of Watson.

ClassifyImageFlow : Run the demo project and with in the browser hit - **http://localhost:8081/classify?url=[URL]**, The result would be a list of all the classes detected in the image.

DetectFacesFlow : In the browser hit - **http://localhost:8081/detectfaces?url=[URL]**, The result would be a list of all the faces, their position, age and gender detected in the image.

RecognizeTextFlow : In the browser hit - **http://localhost:8081/recognizetext?url=[URL]**, The result would be a list of all the words detected in the image.

SUMMARY
-------

Congratulations! You have imported the Watson Visual Recognition Demo project and used the Watson Visual Recognition Connector to extract valuable data from any image. 