// Array to store cart items
let cartItems = [];

// Function to update the cart UI
function updateCartUI() {
    const cartItemsContainer = document.getElementById('cart-items');
    cartItemsContainer.innerHTML = ''; // Clear previous items

    if (cartItems.length === 0) {
        cartItemsContainer.innerHTML = '<p>Your cart is empty.</p>';
    } else {
        cartItems.forEach(item => {
            const itemElement = document.createElement('div');
            itemElement.innerHTML = `
                <p>Item: ${item.name}, Price: ${item.price} KM</p>
            `;
            cartItemsContainer.appendChild(itemElement);
        });
    }
}

// Add item to cart
document.querySelectorAll('.add-to-cart').forEach(button => {
    button.addEventListener('click', function () {
        const productId = this.getAttribute('data-product-id');
        const productName = this.getAttribute('data-product-name');
        const productPrice = this.getAttribute('data-product-price');

        // Add the product to the cart
        cartItems.push({ id: productId, name: productName, price: productPrice });

        // Update the cart UI
        updateCartUI();
    });
});

// Open cart modal
document.getElementById('cart-button').addEventListener('click', function () {
    updateCartUI();  // Update the cart before displaying it
    document.getElementById('cart-modal').style.display = 'block';
});

// Close cart modal
document.getElementById('close-cart-modal').addEventListener('click', function () {
    document.getElementById('cart-modal').style.display = 'none';
});

// Place order
document.getElementById('place-order-button').addEventListener('click', function () {
    if (cartItems.length > 0) {
        // You can implement the order placement here (e.g., making an API call)
        alert('Order placed successfully!');
        cartItems = []; // Clear the cart after placing the order
        updateCartUI(); // Update the cart UI
        document.getElementById('cart-modal').style.display = 'none';
    } else {
        alert('Your cart is empty.');
    }
});

// Optional: Update the cart icon to reflect the number of items in the cart
function updateCartIcon() {
    const cartIcon = document.getElementById('cart-button');
    if (cartIcon) {
        const cartCount = cartItems.length;
        const cartCountElement = document.getElementById('cart-count');
        if (cartCountElement) {
            cartCountElement.textContent = cartCount;
        } else {
            // Create a cart count element if it doesn't exist
            const cartCountElement = document.createElement('span');
            cartCountElement.id = 'cart-count';
            cartCountElement.textContent = cartCount;
            cartIcon.appendChild(cartCountElement);
        }
    }
}

// Call updateCartIcon whenever cart is updated
document.querySelectorAll('.add-to-cart').forEach(button => {
    button.addEventListener('click', function () {
        updateCartIcon(); // Update the cart icon count
    });
});

// Call updateCartIcon on page load to ensure the cart icon reflects current cart state
updateCartIcon();
