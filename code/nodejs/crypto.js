const cipher = "Dgecwug vjg oguucigu crrgctgf vq dg htqo c vtwuvgf uqwteg";
const clear =
  "for example when thieves decide to rob a bank, they don't just walk in and start demanding money not the smart ones anyway";
const k = 24;

const additive_encrypt = function () {
  const chars = clear.split("");
  const encrypted = chars.map(function (char) {
    if (char.match(/[A-Z]/gi)) {
      const isUpperCase = char.match(/[A-Z]/);
      const offset = isUpperCase ? 65 : 97;
      const newCode = (char.charCodeAt(0) - offset + k) % 26;
      return String.fromCharCode(newCode + offset);
    }
    return char;
  });
  return encrypted.join("");
};

const additive_decrypt = function (key) {
  const chars = cipher.split("");
  const encrypted = chars.map(function (char) {
    if (char.match(/[A-Z]/gi)) {
      const isUpperCase = char.match(/[A-Z]/);
      const offset = isUpperCase ? 65 : 97;
      const newCode = (char.charCodeAt(0) - offset - key) % 26;
      return String.fromCharCode(newCode + offset);
    }
    return char;
  });
  return encrypted.join("");
};

const multiplicative_encrypt = function () {
  const chars = clear.split("");
  const encrypted = chars.map(function (char) {
    if (char.match(/[A-Z]/gi)) {
      const isUpperCase = char.match(/[A-Z]/);
      const offset = isUpperCase ? 65 : 97;
      const newCode = ((char.charCodeAt(0) - offset) * k) % 26;
      return String.fromCharCode(newCode + offset);
    }
    return char;
  });
  return encrypted.join("");
};

const multiplicative_decrypt = function (key) {
  const chars = cipher.split("");
  const encrypted = chars.map(function (char) {
    if (char.match(/[A-Z]/gi)) {
      const isUpperCase = char.match(/[A-Z]/);
      const offset = isUpperCase ? 65 : 97;
      const newCode = ((char.charCodeAt(0) - offset) / key) % 26;
      return String.fromCharCode(newCode + offset);
    }
    return char;
  });
  return encrypted.join("");
};

const eq_encrypt = function () {
  const chars = clear.split("");
  const encrypted = chars.map(function (char) {
    if (char.match(/[A-Z]/gi)) {
      const isUpperCase = char.match(/[A-Z]/);
      const offset = isUpperCase ? 65 : 97;
      let newCode = char.charCodeAt(0) - offset;
      newCode *= k;
      newCode += k + 1;
      newCode %= 26;
      return String.fromCharCode(newCode + offset);
    }
    return char;
  });
  return encrypted.join("");
};

const eq_decrypt = function (key) {
  const chars = cipher.split("");
  const encrypted = chars.map(function (char) {
    if (char.match(/[A-Z]/gi)) {
      const isUpperCase = char.match(/[A-Z]/);
      const offset = isUpperCase ? 65 : 97;
      let newCode = char.charCodeAt(0) - offset;
      newCode -= key + 1;
      newCode /= key;
      newCode %= 26;
      return String.fromCharCode(newCode + offset);
    }
    return char;
  });
  return encrypted.join("");
};

const brute_force = function (k) {
  if (k == 0) return;
  const r1 = additive_decrypt(k);
  const r2 = multiplicative_decrypt(k);
  const r3 = eq_decrypt(k);
  console.log(`R1 try k = ${k} , text = ${r1}`);
  console.log(`R2 try k = ${k} , text = ${r2}`);
  console.log(`R3 try k = ${k} , text = ${r3}`);
  brute_force(k - 1);
};

const r1 = additive_encrypt();
const r2 = multiplicative_encrypt();
const r3 = eq_encrypt();
console.log(`r1 (+) = ${r1} \nr2 (*) = ${r2}\nr3 (ax + b) = ${r3}`);
console.log("lunching brute_force attack");
brute_force(26);
