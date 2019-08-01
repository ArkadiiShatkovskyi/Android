
class WorkDay{
  final double _startHour;
  final double _endHour;
  final double _workHours;
  final String _date;
  final double _rate;

  WorkDay(this._startHour, this._endHour, this._workHours, this._date, this._rate);

  double getStartHour(){
    return _startHour;
  }

  double getEndHour(){
    return _endHour;
  }

  double getWorkHours(){
    return _workHours;
  }

  String getDate(){
    return _date;
  }

  double getRate(){
    return _rate;
  }
}