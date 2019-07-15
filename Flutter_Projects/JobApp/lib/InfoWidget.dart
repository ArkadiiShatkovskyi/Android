import 'package:flutter/material.dart';
import 'MenuDrawer.dart';

class InfoWidget extends StatelessWidget{
  final Color titleColor;
  final String bcgImage = null;

  InfoWidget(this.titleColor);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        drawer: MenuDrawer(),
        appBar: AppBar(
          title: Text("About"),
          backgroundColor: this.titleColor,
        ),
        body: Container(
          decoration: BoxDecoration(
            image: DecorationImage(
              image: AssetImage("assets/images/bckg3.jpg"),
                fit: BoxFit.cover
            )
          ),
          child: ListView(
              padding: EdgeInsets.all(15.0),
              children: <Widget>[
                Card(
                    child: Container(
                      width: 400,
                      height: 400,
                      child: Image(
                        image: AssetImage("assets/images/aboutImage.jpg"),
                      ),
                    ),
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
      )
    );
  }
}