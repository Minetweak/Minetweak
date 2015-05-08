#!/usr/bin/env bash

mkdir mcp
cd mcp
wget http://www.modcoderpack.com/website/sites/default/files/releases/mcp910.zip
unzip mcp910.zip
wget https://s3.amazonaws.com/Minecraft.Download/versions/1.8/minecraft_server.1.8.jar -P jars
./decompile.sh --server
cp -r src/ vanillaSrc/
