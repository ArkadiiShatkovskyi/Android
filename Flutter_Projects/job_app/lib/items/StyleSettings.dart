import 'package:flutter/material.dart';

Color _hexToColor(String code) {
  return new Color(int.parse(code.substring(1, 7), radix: 16) + 0xFF000000);
}

final Color styleColor = _hexToColor("#7d93fe");
double defaultRate = 15;
