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
          home: Scaffold(
            appBar: AppBar(
                title: Text("Test here :)"),
                backgroundColor: Colors.lightBlueAccent,
            ),
            body: Container(
              decoration: BoxDecoration(
                  image: DecorationImage(
                    image: AssetImage(bcgImage),
                    fit: BoxFit.cover
                  )
              ),
            ),
            drawer: MenuDrawer(),
          ),
        );
  }
}