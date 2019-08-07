import 'package:flutter/material.dart';
import 'package:ant_icons/ant_icons.dart';
import 'package:job_app/items/StyleSettings.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:job_app/pages/authorizationPage/Authorization.dart';

class AddTab extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => _AddTabState();
}

class _AddTabState extends State<AddTab>{
  TimeOfDay _strTime = TimeOfDay.now();
  TimeOfDay _endTime = TimeOfDay.now();
  DateTime _date =  DateTime.now();
  TextEditingController _rateController = TextEditingController();
  String _user;

  @override
  void initState(){
    super.initState();
    Authorization _db = new Authorization();
    _db.getUser().then((currUser) {this._user = currUser.uid;});
    _rateController.text = defaultRate.toString();
  }

  @override
  Widget build(BuildContext context) {
    return _createBody();
  }

  Widget _createBody(){
    return GridView.count(
      physics: NeverScrollableScrollPhysics(),
      crossAxisCount: 5,
      children: <Widget>[
        const Text(""),
        const Text(""),
        const Center(child:Text("Add work", style: TextStyle(fontSize: 18))),
        const Text(""),
        const Text(""),

        const Text(""),
        Center(child:Text("Choose start time", style: TextStyle(fontSize: 16))),
        Center(child:Text(_strTime.toString().substring(10, 15))),
        IconButton(
          icon: Icon(AntIcons.clock_circle_outline, semanticLabel: "test",),
          iconSize: 40,
          color: styleColor,
          onPressed: (){
            _showStartTimePicker(context);
          },
        ),
        const Text(""),

        const Text(""),
        const Center(child:Text("Choose end time", style: TextStyle(fontSize: 16))),
        Center(child:Text(_endTime.toString().substring(10, 15))),
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
        Center(child:Text(_date.toString().substring(5, 10))),
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
        Center(child: TextField(
          controller: _rateController,
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
          onPressed: (){
            _addHours();
          },
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
      });
  }

  void _addHours() async{
    double workTime = (_endTime.hour + _endTime.minute/60.0) - (_strTime.hour + _strTime.minute/60.0);
    await Firestore.instance.collection(_user)
        .add(
        {
          'date': _date.toString().substring(5, 10),
          'strTime': _strTime.toString().substring(10, 15),
          'endTime': _endTime.toString().substring(10, 15),
          'workTime': workTime,
          'rate': double.parse(_rateController.text),
        });
  }
}