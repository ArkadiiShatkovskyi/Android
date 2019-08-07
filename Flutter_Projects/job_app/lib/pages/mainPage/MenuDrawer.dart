import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

import 'package:job_app/pages/mainPage/DrawerCard.dart';
import 'package:job_app/items/StyleSettings.dart';
import 'package:ant_icons/ant_icons.dart';

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
                  color: Colors.white,
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
            DrawerCard("Account", iconsColor, drawerFont, iconsSize, tilesFontSize, AntIcons.user),        //not added
            DrawerCard("Settings", iconsColor, drawerFont, iconsSize, tilesFontSize, AntIcons.setting_outline),             //not added
            DrawerCard("About", iconsColor, drawerFont, iconsSize, tilesFontSize, AntIcons.info_circle_outline),
            DrawerCard("Close", iconsColor, drawerFont, iconsSize, tilesFontSize, AntIcons.close_square_outline),           //modify
          ]
      );
      }else{
      return ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            Divider(height: 50, color: styleColor),
            _drawerStyle(),
            DrawerCard("Account", iconsColor, drawerFont, iconsSize, tilesFontSize, AntIcons.user),        //not added
            DrawerCard("Calendar", iconsColor, drawerFont, iconsSize, tilesFontSize, AntIcons.calendar_outline),
            DrawerCard("Settings", iconsColor, drawerFont, iconsSize, tilesFontSize, AntIcons.setting_outline),             //not added
            DrawerCard("About", iconsColor, drawerFont, iconsSize, tilesFontSize, AntIcons.info_circle_outline),
            DrawerCard("Close", iconsColor, drawerFont, iconsSize, tilesFontSize, AntIcons.close_square_outline),           //modify
          ]
      );
    }
  }
}