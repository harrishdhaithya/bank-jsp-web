function userSignup(event){
    event.preventDefault();
    const fname = document.getElementById("fname").value;
    const lname = document.getElementById("lname").value;
    const email = document.getElementById("email").value;
    const phone = document.getElementById("phone").value;
    const deposit = document.getElementById("deposit").value;
    const password = document.getElementById("password").value;
    if(
        !fname ||
        !lname ||
        !email ||
        !phone ||
        !deposit ||
        !password
    ){
        alert('All the fields are required...');
        return;
    }
    fetch("user/signup",{
        method:"POST",
        headers:{
            "Content-type":"application/json"
        },
        body:JSON.stringify({fname,lname,email,phone,deposit,password})
    }).then(resp=>{
        if(resp.status==200){
            if(resp.redirected){
                alert('Signup successfull.Go ahed and login.');
                location.href=resp.url;
                return;
            }
        }else{
            resp.text().then(res=>{
                alert('Something went wrong...')
            });
            console.log("Something went wrong...");
            location.reload();
            return;
        }
    }).catch(err=>{
        alert(err);
    });
}