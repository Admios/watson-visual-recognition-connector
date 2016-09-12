Classify URL Image Demo
===========================

INTRODUCTION
------------
The Retrieve Classify URL Image Demo project offer one use case using this flow:

* **RegisterCarAccidentFlow** - Automatize the registration of new car accidents for an Insurance, classifying the images with Watson first, so based on the classification you can validate that the image of the accident is valid and avoid to pass invalid cases to a human operator.


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

- **RegisterCarAccidentFlow**

Run the demo project and in the browser hit - **http://localhost:8081/car-accident?client=[CLIENT NAME]&description=[DESCRIPTION]&image=[IMAGE URL]**:

**Input**: Using a image that contain a car - http://localhost:8081/car-accident?client=test&description=An accident in Panama&image=http://thejmhlawfirm.com/wp-content/uploads/2015/07/car-wreck-one.jpg

**Response**: The result will be the request ready to be created in the insurance system, because the image contain a car.

```javascript
{
  "client": "test",
  "description": "An accident in Panama",
  "image": "http://thejmhlawfirm.com/wp-content/uploads/2015/07/car-wreck-one.jpg"
}
```

**Input**: Using a image that doesn't contain a car - http://localhost:8081/car-accident?client=test&description=An accident in Panama&image=http://www.capatec.org.pa/wp-content/uploads/2014/08/Logo-Admios-.jpg

**Response**: The result will be an error saying that the image doesn't contain a car.

```javascript
{
    "status": "Error",
    "Description": "The image is invalid, doesn't contain any car"
}
```

SUMMARY
-------

Congratulations! You have imported the Classify URL Image Demo project and used the Watson Visual Recognition Connector to make business decisions based on the content of an image. 