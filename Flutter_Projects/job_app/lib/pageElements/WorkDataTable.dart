import 'package:flutter/material.dart';
import 'package:job_app/items/WorkDay.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:job_app/items/Authorization.dart';

// ignore: must_be_immutable
class WorkDataTable extends StatefulWidget{

  @override
  State<StatefulWidget> createState() => _WorkDataTableState();
}

class _WorkDataTableState extends State<WorkDataTable>{
  List<WorkDay> _records;
  String _user;

  DBConnect _db = new DBConnect();

  @override
  void initState(){
    super.initState();
    _records = new List();
    _db.getUser().then((currUser) {this._user = currUser.uid;});
  }

  @override
  Widget build(BuildContext context) {
    double width = 20;
    return new ListView(
      children: <Widget>[
        Container(
        child:Row(
          children: [
            Center(child: const Text("Date")),
            Container(width: width),
            Center(child: const Text("Start time")),
            Container(width: width),
            Center(child: const Text("End time")),
            Container(width: width),
            Center(child: const Text("Rate")),
            Container(width: width),
            Center(child: const Text("Worked time")),
          ],
        ),
        padding: new EdgeInsets.only(left: 30, right: 30),
      ),
        getRows(width + 15),
    ]);
  }

  Widget getRows(width){
    return StreamBuilder(
        stream: Firestore.instance.collection('hoursDB').snapshots(),
        builder: (context, snapshot){
          if(!snapshot.hasData) return const Text('Loading...');
          return ListView.builder(
            shrinkWrap: true,
            physics: NeverScrollableScrollPhysics(),
            itemCount: snapshot.data.documents.length,
            itemBuilder: (context, index) => getRow(snapshot.data.documents[index], width),
          );
        });
  }

  Widget getRow(document, width){
    return Container(
      child:Row(
        children: [
          Center(child: Text(document['date'].toString())),
          Container(width: width),
          Center(child: Text(document['strHour'].toString())),
          Container(width: width),
          Center(child: Text(document['endHour'].toString())),
          Container(width: width),
          Center(child: Text(document['rate'].toString())),
          Container(width: width),
          Center(child: Text(document['workHours'].toString())),
        ],
      ),
      padding: new EdgeInsets.only(left: 30, right: 30),
    );
  }
}