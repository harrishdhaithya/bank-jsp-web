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
            resp.text().then(res=>alert(res));
        }else{
            resp.text().then(res=>{
                alert(res);
            })
        }
        if(resp.redirected){
            location.href=resp.url;
        }
    }).catch(err=>alert(err))
}