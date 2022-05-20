function transactionByDate(event){
    event.preventDefault();
    const date = document.getElementById('date').value;
    if(!date){
        alert('Please enter date...');
    }
    const resultBox = document.getElementById('result');
    const params = new URLSearchParams({date});
    fetch(`transactionByDate.jsp?${params.toString()}`).then(resp=>{
        if(resp.status==200){
            resultBox.classList.remove('hide-box');
            resp.text().then(res=>{
                resultBox.innerHTML=res;
            });
        }
    }).catch(err=>alert(err));
}