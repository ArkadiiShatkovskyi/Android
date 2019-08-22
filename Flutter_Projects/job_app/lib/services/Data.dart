import 'package:cloud_firestore/cloud_firestore.dart';


List<dynamic> _getListOfRates(QuerySnapshot snapshot){
  List<double> tempList = new List();
  return snapshot.documents.map((doc){
    if(tempList.indexOf(doc['rate']) == -1){
      tempList.add(doc['rate'].toDouble());
      return doc['rate'].toDouble();
    }
  }).toList();
}

dynamic _getListOfSalary(QuerySnapshot snapshot, double rate){
  return snapshot.documents.map((doc){
    if(rate == doc['rate'])
      return doc['rate'] * doc['workTime'];
  }).toList();
}

dynamic _getListOfWorkTime(QuerySnapshot snapshot, double rate){
  return snapshot.documents.map((doc){
    if(rate == doc['rate'])
      return doc['workTime'];
  }).toList();
}

List<dynamic> createListOfRates(QuerySnapshot snapshot){
  List<dynamic> temp = _getListOfRates(snapshot);
  temp.removeWhere((value){
    return value == null;
  });
  return temp;
}

List<dynamic> createListOfSalary(QuerySnapshot snapshot, List listOfRates){
  List result = new List();
  for(int i = 0; i < listOfRates.length; i++){
    List<dynamic> temp = _getListOfSalary(snapshot, listOfRates[i]);
    temp.removeWhere((value){
      return value == null;
    });
    result.add(temp.fold(0, (tot, doc) =>  tot + doc));
  }
  return result;
}

List<dynamic> createListOfWorkTime(QuerySnapshot snapshot, List listOfRates){
  List result = new List();
  for(int i = 0; i < listOfRates.length; i++){
    List<dynamic> temp = _getListOfWorkTime(snapshot, listOfRates[i]);
    temp.removeWhere((value){
      return value == null;
    });
    result.add(temp.fold(0, (tot, doc) =>  tot + doc));
  }
  return result;
}