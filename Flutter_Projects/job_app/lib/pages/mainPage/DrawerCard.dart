import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:job_app/pages/InfoPage.dart';

class DrawerCard extends StatelessWidget{
  final String drawerFont;
  final double tilesFontSize;
  final Color iconsColor;
  final double iconsSize;
  final String text;
  final IconData icon;

  DrawerCard(this.text, this.iconsColor, this.drawerFont, this.iconsSize, this.tilesFontSize, this.icon);

  @override
  Widget build(BuildContext context) {
    return Card(child: ListTile(
        leading: Icon(
          icon,
          color:  iconsColor,
          size: iconsSize,
        ),
        title: Text(text,
          style: TextStyle(
            fontFamily: drawerFont,
            fontSize: tilesFontSize,
          ),
        ),
        onTap:(){
          if(text == "About"){
            Navigator.pushReplacement(context, MaterialPageRoute(builder: (context) => InfoWidget()));
          }else if(text == "Account"){
            //Navigator.pushReplacement(context, MaterialPageRoute(builder: (context) => SignInSignUp()));
          }else if(text == "Close"){
            //_showExitDialon(context);
          }
        }
    ),
    );
  }

  void _showExitDialon(BuildContext context){
    showDialog(context: context,
        builder: (BuildContext context){
          return AlertDialog(
            title: new Text("Exit"),
            content: new Text("Are you sure you wanna exit?"),
            actions: <Widget>[
              // usually buttons at the bottom of the dialog
              new FlatButton(
                child: new Text("Exit"),
                onPressed: () {
                  exit(0);
                },
              ),
              new FlatButton(
                child: new Text("Cancel"),
                onPressed: (){
                  Navigator.of(context).pop();
                },
              )
            ],
          );
        }
    );
  }
}