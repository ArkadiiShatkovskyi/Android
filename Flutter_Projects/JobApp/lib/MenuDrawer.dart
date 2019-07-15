import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

import 'DrawerCard.dart';

class MenuDrawer extends StatelessWidget{
  final String drawerFont = 'CourgetteRegular';
  final double tilesFontSize = 20;
  final double iconsSize = 20.0;
  final double titleDrawerSize = 30.0;
  final Color iconsColor = Colors.lightBlueAccent;

  @override
  Widget build(BuildContext context) {
    return Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            Container(
                height: 150.0,
                child: DrawerHeader(
                  child: Center(
                      child: Text("Menu",
                        style: TextStyle(
                          fontFamily: drawerFont,
                          fontSize: titleDrawerSize,
                        ),
                      ),
                  )),
                  decoration: BoxDecoration(
                    image: DecorationImage(
                        image: AssetImage("assets/images/img1.jpg"),
                        fit: BoxFit.cover
                    )
                  ),
                ),
            DrawerCard("Login", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.account_circle),
            DrawerCard("Calendar", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.calendar_today),
            DrawerCard("Settings", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.settings),
            DrawerCard("About", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.info),
            DrawerCard("Exit", iconsColor, drawerFont, iconsSize, tilesFontSize, Icons.exit_to_app),
          ],
        )
    );
  }
}