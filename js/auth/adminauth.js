function adminsignin(event) {
    event.preventDefault();
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    if(!email||!password){
        alert('All the fields are required');
        location.reload();
    }
    fetch("admin/login",{
        method:"POST",
        headers:{
            "Content-type":"application/json"
        },
        body:JSON.stringify({
            email:email,
            password:password
        })
    }).then(resp=>{
        if(resp.status!=200){
            console.log(resp.body);
            location.href='/bank/auth/evalsecret.jsp';
            return;
        }
    }).catch(err=>{
        alert(err);
        console.log(err);
    });
}


function handleCredentialResponse(cred){
    console.log(cred.clientId);
    console.log(cred.credential);
    fetch('/bank/login/google',{
        method:"POST",
        body:JSON.stringify({
        code:cred.credential,
        role:"admin"
    })
    }).then(resp=>{
        if(resp.status==200){
            location.href="/bank/menu/adminmenu.jsp";
            return;
        }else{
            resp.text().then(text=>{
                alert(text);
            });
        }
    })
}
