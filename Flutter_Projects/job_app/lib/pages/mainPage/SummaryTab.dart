import 'package:flutter/material.dart';
import 'package:job_app/pages/authorizationPage/Authorization.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

class SummaryTab extends StatefulWidget{

  @override
  State<StatefulWidget> createState() => _SummaryTabState();
}

class _SummaryTabState extends State<SummaryTab>{
  double _workedTime = 0.0;
  double _salary = 0.0;
  String _user;
  Authorization _authorization = new Authorization();

  @override
  void initState(){
    super.initState();
    _authorization.getUser().then((currUser) {
      this._user = currUser.uid;
      _getValues(currUser.uid);
    });
  }

  void _getValues (String user) {
    Firestore.instance
        .collection(user)
        .snapshots()
        .listen((snapshot) {
          double tempWorkTime = snapshot.documents.fold(0, (tot, doc) {
          return tot + doc.data['workTime'];
      });
      double tempSalary = snapshot.documents.fold(0, (tot, doc) => tot + doc.data['workTime'] * doc.data['rate']);
      setState(() {
        _workedTime = tempWorkTime;
        _salary = tempSalary;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        child: ListView(
          padding: EdgeInsets.only(left: 75, right: 75),
          physics: const NeverScrollableScrollPhysics(),
          children: <Widget>[
            Divider(height: 25, color: Colors.transparent,),
            const Center(child:Text("Summary", style: TextStyle(fontSize: 20),)),
            Divider(height: 25,),
            Container(
                child: Row(
                  children: <Widget>[
                    Container(
                        child: const Text("Time worked: ", style: TextStyle(fontSize: 16),),
                        width: 200,
                    ),
                    Text(_workedTime.toString(), style: TextStyle(fontSize: 16),)
                  ],
            )
            ),
            Container(
                child: Row(
                  children: <Widget>[
                    Container(
                      child: const Text("Salary: ", style: TextStyle(fontSize: 16),),
                      width: 200,
                    ),
                    Text(_salary.toString(), style: TextStyle(fontSize: 16),)
              ],
            )
            ),
          ],)
    );
  }
}