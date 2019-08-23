import 'package:flutter/material.dart';
import 'package:job_app/pages/authorizationPage/Authorization.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:job_app/items/StyleSettings.dart';
import 'package:job_app/services/Data.dart';

class SummaryTab extends StatefulWidget{

  @override
  State<StatefulWidget> createState() => _SummaryTabState();
}

class _SummaryTabState extends State<SummaryTab>{
//  double _workedTime = 0.0;
//  double _salary = 0.0;
  List<dynamic> _listOfRates = new List();
  List<dynamic> _listOfWorkTimePerRate = new List();
  List<dynamic> _listOfSalaryPerRate = new List();
  Authorization _authorization = new Authorization();

  @override
  void initState(){
    super.initState();
    _authorization.getUser().then((currUser) {
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
            _listOfRates = createListOfRates(snapshot);
            _listOfSalaryPerRate = createListOfSalary(snapshot, _listOfRates);
            _listOfWorkTimePerRate = createListOfWorkTime(snapshot, _listOfRates);
            if (this.mounted){
              setState(() {
                _listOfRates.add("Total");
                _listOfWorkTimePerRate.add(tempWorkTime);
                _listOfSalaryPerRate.add(tempSalary);
//                _workedTime = tempWorkTime;
//                _salary = tempSalary;
              });
      }
    });
  }

  Widget _createItem(int index){
    String rate =  _listOfRates[index].toString();
    return new Card(
//      color: Colors.white.withAlpha(230),
//      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(25)),
      child: new Container(
        padding: EdgeInsets.only(left: 75, right: 75),
        child: Column(
          children: <Widget>[
            Divider(height: 30, color: Colors.transparent,),
            Center(child: Text(rate.toString() == "Total" ? "Total" : "Job per rate: $rate", style: TextStyle(fontSize: 18),)),
            Divider(height: 30, color: styleColor,),
            Container(
              child: Row(
                children: <Widget>[
                  Container(
                    child:Row(
                      children: <Widget>[
                        Container(
                          child: const Text("Time worked: ", style: TextStyle(fontSize: 16),),
                          width: 170,
                        ),
                        Text(_listOfWorkTimePerRate[index].toString(), style: TextStyle(fontSize: 16),)
                      ],
                    ),
                  ),
                ],
              ),
            ),
            Divider(height: 10, color: Colors.transparent,),
            Container(
              child:Row(
                children: <Widget>[
                  Container(
                    child: const Text("Salary: ", style: TextStyle(fontSize: 16),),
                    width: 170,
                  ),
                  Text(_listOfSalaryPerRate[index].toString(), style: TextStyle(fontSize: 16),)
                ],
              ),
            ),
            Divider(height: 30, color: Colors.transparent,),
          ],
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
//                    physics: NeverScrollableScrollPhysics(),
                    shrinkWrap: true,
                    itemCount: _listOfRates.length,
                    itemBuilder: (BuildContext ctxt, int index) => _createItem(index)
    );
  }
}