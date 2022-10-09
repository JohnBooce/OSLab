for file in $(find . -type f -name "prog*.c")
do
    compliedName=`echo $file | cut -d'/' -f2 | cut -d'.' -f1`
    rawName=`echo $file | cut -d'/' -f2`
    gcc -o $compliedName $rawName |& grep -rawName 'error' 2>/dev/null
    if [ $compliedName == "progA" ]
    then
        ans=10
    elif [ $compliedName == "progB" ]
    then
        ans=7
    elif [ $compliedName == "progC" ]
    then
        ans=5
    fi
    printf "%s\t\t%d\n" $rawName $ans
done | sort
