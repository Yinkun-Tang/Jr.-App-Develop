function processData(input) {
    //Enter your code here
    NodeRecord = new Set();
    
    while(input != null){
        if(NodeRecord.has(input)){
            return true;
        }
        
        NodeRecord.add(input);
        
        input = input.next;
    }
    
    return false;
}

process.stdin.resume();
process.stdin.setEncoding("ascii");
_input = "";
process.stdin.on("data", function (input) {
    _input += input;
});

process.stdin.on("end", function () {
   processData(_input);
});