import 'package:flutter/material.dart';
import 'package:job_app/pages/mainPage/MainPage.dart';
import 'package:job_app/items/StyleSettings.dart';

class InfoWidget extends StatelessWidget{
  final String bcgImage = null;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: WillPopScope(
          onWillPop: () async => false,
          child: Scaffold(
            appBar: AppBar(
                title: Text("About"),
                backgroundColor: styleColor,
                leading: IconButton(icon:Icon(Icons.arrow_back),
                  onPressed:() => Navigator.pushReplacement(context, MaterialPageRoute(builder: (context) => MainPage())),
                )
            ),
            body: Container(
                child: ListView(
                    padding: EdgeInsets.all(15.0),
                    children: <Widget>[
                      Divider(
                        height: 30,
                        color: Colors.transparent,
                      ),
                      Card(
                        child: Container(
                          width: 300,
                          height: 300,
                          child: Image(
                            image: AssetImage("assets/images/undraw_mobile_prototyping_grmd.png"),
                          ),
                        ),
                      ),
                      new Divider(
                        height: 40.0,
                      ),
                      Card(
                          elevation: 0,
                          color: Colors.transparent.withOpacity(0.0),
                          child:Container(
                            color: Colors.transparent,
                            child: Text(
                              "Hi, i'm Arkadii Shatkovskyi. Android Developer. It's my first app in flutter. I hope you enjoy it. \n Best regards A.S.",
                              textAlign: TextAlign.center,
                              style: TextStyle(fontSize: 17),
                            ),
                          )
                      ),
                    ]
                )
            ),
          ),
      )
    );
  }
}