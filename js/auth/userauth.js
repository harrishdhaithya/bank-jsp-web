function userLogin(event){
    event.preventDefault();
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    if(
        !email ||
        !password
    ){
        alert('All the fields are required...');
        location.reload();
        return;
    }
    fetch("user/login",{
        method:"POST",
        headers:{
            "Content-type":"application/json"
        },
        body:JSON.stringify({email,password})
    }).then(resp=>{
        if(resp.status==200){
            if(resp.redirected){
                location.href=resp.url;
                return;
            }
        }else{
            resp.text().then(resp=>{
                alert(resp);
                location.reload();
                return;
            })
        }
    }).catch(err=>{
        alert(err);
        location.reload();
    });
}