import 'package:flutter/material.dart';
import 'package:ant_icons/ant_icons.dart';
import 'package:job_app/items/StyleSettings.dart';
import 'package:intl/intl.dart';

class BottomSheetWidget extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => _BottomSheetState();
}

class _BottomSheetState extends State<BottomSheetWidget>{

  TimeOfDay _strTime = TimeOfDay.now();
  TimeOfDay _endTime = TimeOfDay.now();
  DateTime _date =  DateTime.now();
  String _rate;
  String _workTime;

  @override
  Widget build(BuildContext context) {
    return Container(
        height: 460,
//        padding: const EdgeInsets.symmetric(horizontal: 5, vertical: 5),
        margin: const EdgeInsets.symmetric(horizontal: 10, vertical: 10),
        decoration: BoxDecoration(
            color: Colors.grey[200],
            borderRadius: BorderRadius.circular(30)
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
          onPressed: (){
            _showStartTimePicker(context);
          },
        ),
        const Text(""),
        const Text(""),
        const Center(child:Text("Choose end time", style: TextStyle(fontSize: 16))),
        const Text(""),
        IconButton(
          icon: Icon(AntIcons.clock_circle_outline),
          iconSize: 40,
          color: styleColor,
          onPressed: (){
            _showEndTimePicker(context);
          },
        ),
        const Text(""),
        const Text(""),
        const Center(child:Text("Choose date", style: TextStyle(fontSize: 16))),
        const Text(""),
        IconButton(
          icon: Icon(AntIcons.calendar_outline),
          iconSize: 40,
          color: styleColor,
          onPressed: (){
            _showDatePicker(context);
          },
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

  Future<Null> _showDatePicker(BuildContext context) async {
    final DateTime picked = await showDatePicker(
        initialDatePickerMode: DatePickerMode.day,
        context: context,
        initialDate: _date,
        firstDate: DateTime(2015, 8),
        lastDate: DateTime(2050));
    if (picked != null && picked != _date)
      setState(() {
        _date = picked;
//        print("date: " + _date.toString().substring(5, 10));
      });
  }

  Future<Null> _showStartTimePicker(BuildContext context) async {
    final TimeOfDay picked = await showTimePicker(
      context: context,
      initialTime: _strTime,
    );
    if (picked != null && picked != _strTime)
      setState(() {
        _strTime = picked;
//        print("str time: " + _strTime.toString().substring(10, 15));
      });
  }

  Future<Null> _showEndTimePicker(BuildContext context) async {
    final TimeOfDay picked = await showTimePicker(
      context: context,
      initialTime: _endTime,
    );
    if (picked != null && picked != _endTime)
      setState(() {
        _endTime = picked;
//        print("end time: " + _endTime.toString().substring(10, 15));
      });
  }
}
