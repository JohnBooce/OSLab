for file in $(find . -type f -name "prog*.c")
do
    trimmedName=`echo $file | cut -d'/' -f2 | cut -d'.' -f1`
    echo $file $trimmedName
done