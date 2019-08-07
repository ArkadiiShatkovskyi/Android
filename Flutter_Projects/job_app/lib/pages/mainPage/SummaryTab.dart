import 'package:flutter/material.dart';
import 'package:job_app/pages/authorizationPage/Authorization.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

class SummaryTab extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => _SummaryTabState();
}

class _SummaryTabState extends State<SummaryTab>{
  double _workedTime;
  double _salary;
  String _user = 'null';
  Authorization _authorization = new Authorization();

  @override
  void initState(){
    super.initState();
    this._salary = 0;
    this._workedTime = 0;
    _authorization.getUser().then((currUser) {this._user = currUser.uid;});
//    _getData().then((val){this._workedTime += val;});
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        child: ListView(
          physics: const NeverScrollableScrollPhysics(),
          children: <Widget>[
            const Center(child:Text("Summary", style: TextStyle(fontSize: 20),)),
            Container(child: Row(
              children: <Widget>[
                const Text("Time worked: "),
                Text(_workedTime.toString())
              ],
            )
            ),
            Container(child: Row(
              children: <Widget>[
                const Text("Salary: "),
                Text(_salary.toString())
              ],
            )
            ),
          ],)
    );
  }

  /*Future<double> _getData() async {
    Firestore.instance.collection(_user).snapshots().listen(
            (data){
             data.documents.map((DocumentSnapshot documentSnapshot){
               setState(() {
                 _salary += _workedTime * documentSnapshot['rate'];
                 return documentSnapshot['workTime'];
               });
             });
            });
    return 0;
  }*/
}