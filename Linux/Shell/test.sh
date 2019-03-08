# build docker images

:<<!
application_names=( "ymfront-center" "ymfront-gm" "ymfront-ms/ymfront-finchina-ms" "ymfront-route" "ymfront-admin" )
for appname in ${application_names[@]}
do
echo $appname
done

int_arr=(1 2 3 4 5 6 7)
for var in ${int_arr}
do
echo ${var}
done


for inp in "$*"
do
echo $inp
done


echo $(ls -al) | grep -name tesh.sh


imagesName=(ymfront-center ymfront-gm ymfront-finchina-ms ymfront-route ymfront-admin)
for imageName in ${imagesName[@]};do
    echo ${imageName}
done
for imageName in ${imagesName[@]};do
if [ "${imageName}" == "ymfront-finchina-ms" ]; then
#docker build -t ${imageName}:$1 ymfront-ms/${imageName}/
echo "equal"
else
#docker build -t ${imageName}:$1 ${imageName}/
echo "notEqual"
fi
done
rm=$(docker ps -aq|awk '{print $1}')
rm="10"
if [ "$rm" != "" ]; then
#docker rm -f $(docker ps -aq)
echo "exist${rm}"
fi

!

imagesName=(ymfront-center ymfront-gm ymfront-finchina-ms ymfront-route ymfront-admin)
for imageName in ${imagesName[@]}
do
docker build tag "${imageName}:$1" hub.c.163.com/shuangtest/"${imageName}:$1"
#docker push hub.c.163.com/shuangtest/${imageName}
done



