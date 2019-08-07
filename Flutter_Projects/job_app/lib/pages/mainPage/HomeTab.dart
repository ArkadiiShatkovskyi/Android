import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:job_app/pages/authorizationPage/Authorization.dart';

class WorkDataTable extends StatefulWidget{

  @override
  State<StatefulWidget> createState() => _WorkDataTableState();
}

class _WorkDataTableState extends State<WorkDataTable>{

  String _user;
  Authorization _db = new Authorization();

  @override
  void initState(){
    super.initState();
    _getUser();
  }

  void _getUser() async{
    var firebaseUser =  await _db.getUser();
    setState(() {
      _user = firebaseUser.uid;
    });
  }

  @override
  Widget build(BuildContext context) {
    return StreamBuilder(
        stream: Firestore.instance.collection('hoursDB').where("user", isEqualTo: _user).snapshots(),
        builder: (context, snapshot){
          if(!snapshot.hasData) return const Text('Loading...');
          return new DataTable(
            columnSpacing: 15,
            columns: [
              DataColumn(
                label: const Text("Date"),
              ),
              DataColumn(
                label: const Text("Start time"),
              ),
              DataColumn(
                label: const Text("End time"),
              ),
              DataColumn(
                label: const Text("Work time"),
              ),
              DataColumn(
                label: const Text("Rate"),
              ),
            ],
            rows: _createRows(snapshot.data));
        });
  }

  List<DataRow> _createRows(QuerySnapshot snapshot) {
      List<DataRow> newList = snapshot.documents.map((DocumentSnapshot documentSnapshot) {
      return new DataRow(
          cells: [
            DataCell(Text(documentSnapshot['date'].toString())),
            DataCell(Text(documentSnapshot['strHour'].toString())),
            DataCell(Text(documentSnapshot['endHour'].toString())),
            DataCell(Text(documentSnapshot['workHours'].toString())),
            DataCell(Text(documentSnapshot['rate'].toString())),
          ]
      );
    }).toList();
    return newList;
  }
}