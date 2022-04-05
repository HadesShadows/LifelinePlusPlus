var account;
window.addEventListener('load', async () => {


    if (typeof window.ethereum !== 'undefined') {
        console.log("MetaMask is Available :) !");
    }

    // Modern DApp browsers
    if (window.ethereum) {
        window.web3 = new Web3(ethereum);

        // To prevent the page reloading when the MetaMask network changes
        ethereum.autoRefreshOnNetworkChange = false;

        // To Capture the account details from MetaMask
        const accounts = await ethereum.enable();
        account = accounts[0];
        console.log(account);
        document.getElementById("content").innerHTML = account;

    }
    // Legacy DApp browsers
    else if (window.web3) {
        //window.web3 = new Web3(web3.currentProvider);
        window.web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/cbd9dc11b30147e9a2cc974be655ef7c"));
    }
    // Non-DApp browsers
    else {
        console.log('Non-Ethereum browser detected. Please install MetaMask');
    }

});
