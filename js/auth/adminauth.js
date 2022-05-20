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
        }
        resp.text().then(res=>console.log(res));
        if(resp.redirected){
            location.href=resp.url;
        }
    }).catch(err=>{
        alert(err);
        console.log(err);
    });
}
