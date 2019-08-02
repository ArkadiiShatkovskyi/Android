import 'package:flutter/material.dart';
import 'package:ant_icons/ant_icons.dart';
import 'package:job_app/items/StyleSettings.dart';

class BottomSheetWidget extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => _BottomSheetState();
}

class _BottomSheetState extends State<BottomSheetWidget>{

  @override
  Widget build(BuildContext context) {
    return Container(
        height: 460,
//        padding: const EdgeInsets.symmetric(horizontal: 5, vertical: 5),
        margin: const EdgeInsets.symmetric(horizontal: 10, vertical: 10),
        decoration: BoxDecoration(
            color: Colors.grey[200],
            borderRadius: BorderRadius.circular(10)
        ),
        child: _createBody(),
    );
  }

  Widget _createBody(){
    return GridView.count(
      physics: NeverScrollableScrollPhysics(),
      crossAxisCount: 5,
      children: <Widget>[
        const Text(""),
        const Text(""),
        const Center(child:Text("Add work", style: TextStyle(fontSize: 18),)),
        const Text(""),
        const Text(""),
        const Text(""),
        Center(child:Text("Choose start time", style: TextStyle(fontSize: 16))),
        const Text(""),
        IconButton(
          icon: Icon(AntIcons.clock_circle_outline),
          iconSize: 40,
          color: styleColor,
          onPressed: (){},
        ),
        const Text(""),
        const Text(""),
        const Center(child:Text("Choose end time", style: TextStyle(fontSize: 16))),
        const Text(""),
        IconButton(
          icon: Icon(AntIcons.clock_circle_outline),
          iconSize: 40,
          color: styleColor,
          onPressed: (){},
        ),
        const Text(""),
        const Text(""),
        const Center(child:Text("Choose end time", style: TextStyle(fontSize: 16))),
        const Text(""),
        IconButton(
          icon: Icon(AntIcons.calendar_outline),
          iconSize: 40,
          color: styleColor,
          onPressed: (){},
        ),
        const Text(""),
        const Text(""),
        Center(child:Text("Write your rate", style: TextStyle(fontSize: 16))),
        const Text(""),
        Center(child:TextField(
          obscureText: false,
          decoration: InputDecoration(
            border: OutlineInputBorder(borderRadius: BorderRadius.circular(20)),
            labelText: 'Rate',
          ),
        )
        ),
        const Text(""),
        const Text(""),
        const Text(""),
        IconButton(
          icon: Icon(AntIcons.save),
          iconSize: 40,
          color: styleColor,
          onPressed: (){},
        ),
        const Text(""),
        const Text(""),
      ],
    );
  }
}
