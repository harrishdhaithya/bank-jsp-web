function searchUser(event){
    event.preventDefault();
    const accno = document.getElementById("accno").value;
    if(!accno){
        alert('Enter the Account Number...');
        return;
    }
    const resultBox = document.getElementById('result');
    const param = new URLSearchParams({
        accno:accno.trim()
    });
    fetch(`userByAccno.jsp?${param.toString()}`,{
        method:"GET",
    }).then(resp=>{
        if(resp.status==200){
            resp.text().then(res=>{
                resultBox.classList.remove('hide-box');
                resultBox.innerHTML=res;
            });
            return;
        }else{
            alert('Something went wrong...');
        }
    }).catch(err=>alert(err));
}
