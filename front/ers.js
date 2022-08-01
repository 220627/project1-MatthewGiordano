const url = "http://localhost:3000"

document.getElementById("getReimbButton").onclick = getReimbursements

async function getReimbursements(){

    let response = await fetch(url + "/reimbursements")

    console.log(response)
        if(response.status === 200){

            let data = await response.json()
            for(let reimbursements of data){

                let row = document.createElement("tr")

                let cell = document.createElement("td")

                cell.innerHTML = reimbursements.reimb_id

                row.appendChild(cell)

                //cell 2
                let cell2 = document.createElement("td")
                cell2.innerHTML = reimbursements.reimb_amount
                row.appendChild(cell2)

                //cell 3
                let cell3 = document.createElement("td")
                cell3.innerHTML = reimbursements.reimb_submitted
                row.appendChild(cell3)

                //cell 4
                let cell4 = document.createElement("td")
                cell4.innerHTML = reimbursements.reimb_resolved
                row.appendChild(cell4)

                let cell5 = document.createElement("td")
                cell5.innerHTML = reimbursements.reimb_description
                row.appendChild(cell5)

                let cell6 = document.createElement("td")
                cell6.innerHTML = reimbursements.reimb_author
                row.appendChild(cell6)

                let cell7 = document.createElement("td")
                cell7.innerHTML = reimbursements.reimb_resolver
                row.appendChild(cell7)

                let cell8 = document.createElement("td")
                cell8.innerHTML = reimbursements.reimb_status_id
                row.appendChild(cell8)

                let cell9 = document.createElement("td")
                cell9.innerHTML = reimbursements.reimb_type_id
                row.appendChild(cell9)
                    
                document.getElementById("reimbBody").appendChild(row)

            }

        } else {
            alert("something went wrong! make sure your java is running")
        }

}