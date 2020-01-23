#!/bin/bash
filename=$1
egrep -o "\b[[:alpha:]]+\b" $filename | awk '{count[$0]++}END{for(ind in count){printf("%-14s%d\n",ind,count[ind]);}}' |sort -k 2n -r|tail -10