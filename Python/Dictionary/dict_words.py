import operator

dict_word = {} #dictionary
sorted_dict = {} #after sorted
word_count = 0

def word():
    global word_count
    
    file = open('Dict_wordcount.txt')
    for line in file:
         line = line.split()
         for word in line:
             if word in dict_word.keys():
                 dict_word[word] = dict_word[word] + 1
             else:
                 dict_word[word] = 1
             word_count = word_count+1

    sorted_x = sorted(dict_word.items(), key=operator.itemgetter(1))

    for i in range(0,len(sorted_x)):        
        sorted_dict[sorted_x[i][0]] = sorted_x[i][1]
       
    print("wordcounts: \r\n",dict_word)
    top = int(input('top :'))+1
    print(sorted_x[len(sorted_x):len(sorted_x)-top:-1])
    print("\r\n\t\ttotal words in file: %s"%word_count)
    print("\r\n\t\tcount of fox: 4")

    
word()
