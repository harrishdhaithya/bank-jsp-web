function verifyOTP(event){
    event.preventDefault();
    const otp = document.getElementById("otp").value;
    if(!otp){
        alert('Please enter OTP...');
        return;
    }
    const params = new URLSearchParams({otp:otp.trim()});
    fetch(`user/otp?${params.toString()}`)
    .then(resp=>{
        if(resp.status==200){
            resp.text().then(res=>{
                const secret = res;
                const param = new URLSearchParams({secret});
                location.href=`/bank/auth/showsecret.jsp?${param.toString()}`;
            })
        }else{
            resp.text().then(res=>{
                alert(res);
            })
            location.href="/bank/auth/usersignup.jsp";
        }
        
    }).catch(err=>alert(err))
}