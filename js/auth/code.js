function evalCode(event){
    event.preventDefault();
    const code = document.getElementById("code").value;
    if(!code){
        alert('Enter a valid code...');
        return;
    }
    const param = new URLSearchParams({code:code.trim()});
    fetch(`user/secret?${param.toString()}`).then(resp=>{
        if(resp.status==200){
            resp.json().then(res=>{
                if(res.role=='admin'){
                    location.href='/bank/menu/adminmenu.jsp'
                }else{
                    location.href='/bank/menu/usermenu.jsp'
                }
            })
        }else{
            resp.text().then(res=>{
                alert(res);
            });
            location.href="/bank/auth/userlogin.jsp";
        }
    });
}