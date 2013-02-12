FuzzyDeduperJava
================

This project is deprecated in favor of the Scala or Akka versions.

Summary
--------
Java tool for finding potential duplicates in a list of strings. Takes into consideration misspellings and typos.


Problem
--------
There exists a list of names in the form of Strings, some of which are near-duplicates. We need to determine which are most likely duplicates so a human can review the list.

Solution
--------
* For each name, enumerate its subsequences ("cat" -> "c", "a", "t", "ca", "at")
* Invert this mapping so that the subsequences map to names
* Find candidate duplicates based on subsequence matches
* Score the candidates
