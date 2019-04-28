# build docker images

# :<<!
# application_names=( "ymfront-center" "ymfront-gm" "ymfront-ms/ymfront-finchina-ms" "ymfront-route" "ymfront-admin" )
# for appname in ${application_names[@]}
# do
# echo $appname
# done

echo "hello world"
# echo "$$"
# echo "$!"
# echo "$-"
# echo "$?"

int_arr=(1 2 3 4 5 6 7)

echo "${#int_arr[*]}"

for var in ${int_arr[@]}; do
echo "value = ${var}"
done

echo $((2 + 3))
echo `expr 2 + 4`


