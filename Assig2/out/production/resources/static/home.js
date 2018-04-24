function addRow(book)
{
    if (!document.getElementsByTagName) return;
    tabBody=document.getElementsByTagName("tbody").item(0);
    row=document.createElement("tr");
    cell1 = document.createElement("td");
    cell2 = document.createElement("td");
    textnode1=document.createTextNode(content);
    textnode2=document.createTextNode(morecontent);
    cell1.appendChild(textnode1);
    cell2.appendChild(textnode2);
    row.appendChild(cell1);
    row.appendChild(cell2);
    tabBody.appendChild(row);


}