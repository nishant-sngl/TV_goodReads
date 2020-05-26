How to execute:

    We need to run the whatsthatbookname.sh file with the input string as parameter.
    eg: sh whatsthatbookname.sh "Book name"
    eg: sh whatsthatbookname.sh "#books bookname #groups group name #quotes my quote name";
    
Input string format:

    Input string can be accessed with or without hashtags.
    If there are no hash tags then the string will be searched for book name.
    Else it will be processed as per the given tags:
        #books #groups #quotes
        Current implementation is for these 3 tags.
    eg. "#books bookname #groups group name #quotes my quote name";
    the above string will be processed in the following way:
        The value for @books ie bookname will be searched under books link on search page.
        The value for @groups ie "group name" will be searched under groups link on search page.
        The value for @quotes ie "my quote name" will be searched under quotes link on search page.
        From each search result it will store a list of book urls.
        Then it will store the book urals in a hash map with the number of their count occurences.
        If any book has appeared in all the 3 search results then it will be the desired result.
        The map will be sorted on the basis of values. so the book occuring most number of times will come on top.
        Then print the 1st url in map.
        
Books Name and Group:
    
    For books name it will check the book title and store it in list with URL.
    
Quotes:

    It will store the book url given in ().
         
Limitations:

    Current processing is only on the basis of these tags: #books #groups #quotes
    
    If the there are no hastags, then it will be searched under book name.
    This can be extended to be searched under all the links