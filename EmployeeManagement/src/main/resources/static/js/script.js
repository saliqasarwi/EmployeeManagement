// script.js

// Function to validate the edit profile form
function validateEditProfileForm() {
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const email = document.getElementById('email').value;

    if (!firstName || !lastName || !email) {
        alert('Please fill out all fields.');
        return false;
    }

    if (!validateEmail(email)) {
        alert('Please enter a valid email address.');
        return false;
    }

    return true;
}

// Function to validate email format
function validateEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}

// Function to show a success message
function showSuccessMessage(message) {
    const successMessage = document.createElement('div');
    successMessage.className = 'alert alert-success';
    successMessage.textContent = message;

    const form = document.querySelector('form');
    form.parentNode.insertBefore(successMessage, form.nextSibling);

    setTimeout(() => {
        successMessage.remove();
    }, 3000);
}

// Example: Add event listener to the edit profile form
document.addEventListener('DOMContentLoaded', () => {
    const editProfileForm = document.querySelector('form');
    if (editProfileForm) {
        editProfileForm.addEventListener('submit', (event) => {
            if (!validateEditProfileForm()) {
                event.preventDefault();
            } else {
                // Simulate form submission success
                showSuccessMessage('Profile updated successfully!');
            }
        });
    }
});