function changeFilter() {
    const filter = document.getElementById("filter").value;
    const accbox = document.getElementById('acc-box');
    const datebox = document.getElementById('date-box');
    if(filter=="accno"){
        if(accbox.classList.contains('hide-box')){
            accbox.classList.remove('hide-box');
        }
        if(!datebox.classList.contains('hide-box')){
            datebox.classList.add('hide-box');
        }
    }else if(filter=="date"){
        if(datebox.classList.contains('hide-box')){
            datebox.classList.remove('hide-box');
        }
        if(!accbox.classList.contains('hide-box')){
            accbox.classList.add('hide-box');
        }
    }else{
        if(!accbox.classList.contains('hide-box')){
            accbox.classList.add('hide-box');
        }
        if(!datebox.classList.contains('hide-box')){
            datebox.classList.add('hide-box');
        }
    }
}
function extractDate(event){
    event.preventDefault();
    const filter = document.getElementById('filter').value;
    const format = document.getElementById('format').value;
    var params;
    if(filter=="none"){
        params=new URLSearchParams({
            filter,format
        });
    }else if(filter=="accno"){
        const accno = document.getElementById('accno').value;
        params=new URLSearchParams({
            filter,format,accno
        });
    }else if(filter=="date"){
        const date = document.getElementById('date').value;
        params= new URLSearchParams({
            filter,format,date
        });
    }
    const ext = (format=='pdf')?'pdf':'xlsx'
    fetch(`/bank/record/generate?${params.toString()}`).then(resp=>{
        if(resp.status==200){
            resp.blob().then(blob=>{
                const url = URL.createObjectURL(blob);
                console.log(url);
                const a = document.createElement('a');
                a.style.display="none";
                a.href=url;
                a.download='transactions.'+ext;
                a.click();
                URL.revokeObjectURL(url);
            }   
            )
        }else{
            alert('Something went wrong...');
        }
    })
}