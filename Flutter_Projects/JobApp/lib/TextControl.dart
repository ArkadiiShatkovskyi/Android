import 'package:flutter/material.dart';
import 'MenuDrawer.dart';

class TextControl  extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => _WidgetState();
}

class _WidgetState extends State<TextControl>{
  final String bcgImage = "assets/images/bckg3.jpg";

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
          debugShowCheckedModeBanner: false,
          home: WillPopScope(
            onWillPop: () async => false,
            child: Scaffold(
              drawer: MenuDrawer(2),
              appBar: AppBar(
                title: Text("Calendar"),
                backgroundColor: Colors.lightBlueAccent,
              ),
              body: Container(
                /**
                    decoration: BoxDecoration(        ///Background
                    image: DecorationImage(
                    image: AssetImage(bcgImage),
                    fit: BoxFit.cover
                    )
                    ),
                 **/
              ),
            ),
          )
    );
  }
}