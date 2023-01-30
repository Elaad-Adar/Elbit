from flask import Flask, jsonify
import subprocess

app = Flask(__name__)


@app.route('/containers')
def containers():
    output = subprocess.run(['docker', 'ps'], capture_output=True)
    return jsonify(output.stdout.decode())


if __name__ == '__main__':
    app.run(debug=True, host='localhost', port=5000)
