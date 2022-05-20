function transByAccno(event) {
    event.preventDefault();
    const accno = document.getElementById('accno').value;
    if(!accno){
        alert('please Enter Account Number...');
        return;
    }
    const resultBox = document.getElementById('result');
    const param = new URLSearchParams({
        accno:accno.trim()
    });
    fetch(`transByAccno.jsp?${param.toString()}`).then(resp=>{
        if(resp.status==200){
            resp.text().then(res=>{
                resultBox.classList.remove('hide-box');
                resultBox.innerHTML=res;
            })
        }
    }).catch(err=>alert(err));
}