import 'package:flutter/material.dart';
import 'package:flutter_calendar/flutter_calendar.dart';

class CalendarViewApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new CalendarState();
  }
}

class CalendarState extends State<CalendarViewApp>{

  void handleNewDate(date) {
    print("handleNewDate ${date}");
  }

  @override
  Widget build(BuildContext context) {
    return new Container(
          padding: EdgeInsets.all(15.0),
          child: new ListView(
            shrinkWrap: true,
            children: <Widget>[
              new Calendar(),
              new Divider(
                height: 50.0,
              ),
            ],
          ),
    );
  }
}