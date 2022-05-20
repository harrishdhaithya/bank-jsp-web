function makeTransaction(event){
    event.preventDefault();
    const accno = document.getElementById("accno").value;
    const amount = document.getElementById("amount").value;
    if(
        !accno||
        !amount
    ){
        alert('All the fields are required...');
    }
    fetch("/bank/user/transaction",{
        method:"POST",
        headers:{
            "Content-type":"application/json"
        },
        body:JSON.stringify({accno,amount})
    }).then(resp=>{
        if(resp.status==200){
            alert('Transaction Successfull...')
            location.reload();
        }else{
            if(resp.redirected){
                location.href=resp.url;
            }
            alert('Transaction Failed...');
        }
    }).catch(err=>alert(err));
}