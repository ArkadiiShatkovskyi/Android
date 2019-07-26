import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

import 'package:job_app/items/DrawerCard.dart';
import 'package:job_app/StyleSettings.dart';

class MenuDrawer extends StatelessWidget{
  final String drawerFont = 'CourgetteRegular';
  final double tilesFontSize = 20;
  final double iconsSize = 20.0;
  final double titleDrawerSize = 30.0;
  final Color iconsColor = styleColor;
  final int menuItem;

  MenuDrawer(this.menuItem);

  @override
  Widget build(BuildContext context) {
    return Drawer(
        child: _getList(),
    );
  }

  Container _drawerStyle(){
      return Container(
        decoration: BoxDecoration(color: styleColor),
        height: 175.0,
        child: DrawerHeader(
            child: Center(
              child: Text("Menu",
                style: TextStyle(
                  fontFamily: drawerFont,
                  fontSize: titleDrawerSize,
                ),
              ),
            )),
      );
  }

  ListView _getList(){
    if(this.menuItem == 2) {
      return ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            _drawerStyle(),
            Divider(height: 10, color: Colors.transparent),
//            DrawerCard("Account", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.account_circle),        //not added
//            DrawerCard("Summary", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.insert_invitation),     //not added
//            DrawerCard("Settings", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.settings),             //not added
            DrawerCard("About", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.info),
            DrawerCard("Log out", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.exit_to_app),           //modify
          ]
      );
      }else{
      return ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            Divider(height: 50, color: styleColor),
            _drawerStyle(),
//            DrawerCard("Account", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.account_circle),        //not added
            DrawerCard("Calendar", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.calendar_today),
//            DrawerCard("Summary", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.insert_invitation),     //not added
//            DrawerCard("Settings", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.settings),             //not added
            DrawerCard("About", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.info),
            DrawerCard("Log out", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.exit_to_app),           //modify
          ]
      );
    }
  }
}