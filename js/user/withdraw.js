function makeWithdrawal(event) {
    event.preventDefault();
    const amount = document.getElementById("amount").value;
    if(!amount){
        alert('Enter a valid field...');
        location.reload();
        return;
    }
    fetch('/bank/user/withdraw',{
        method:"POST",
        headers:{
            "Content-type":"application/json"
        },
        body:JSON.stringify({amount})
    }).then(resp=>{
        if(resp.status==200){
            resp.text().then(res=>{
                alert(res);
                location.reload();
            })
        }else{
            if(resp.redirected){
                location.href=resp.url;
                return;
            }else{
                resp.text().then(res=>{
                    alert(res);
                })
            }
        }
    })

}